package com.whoops.product.service;

import com.whoops.product.pojo.Product;
import com.whoops.product.pojo.ProductStock;
import com.whoops.product.repository.ProductRepository;
import com.whoops.product.repository.ProductStockRepository;
import com.whoops.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductStockRepository productStockRepository;

    public List<Product> loadAllProduct(){
        return productRepository.findAll();
    }

    public Product findProductById(Long id){
        return productRepository.getOne(id);
    }

    public Response saveProduct(Product product){
        Product existedProduct = productRepository.findByNameAndColorAndSize(product.getName(),product.getColor(),product.getSize());
        if(existedProduct != null){
           return new Response(false,"已存在该种成品!");
        }else if(product.getId() != null && product.getId() != 0L){
            Product oldProduct = productRepository.getOne(product.getId());
            oldProduct.setName(product.getName());
            oldProduct.setSize(product.getSize());
            oldProduct.setColor(product.getColor());
            Product editedProduct = productRepository.save(oldProduct);
            if(editedProduct != null){
                return new Response(true,"修改成功!");
            }
            return  new Response(false,"修改失败!");
        }else{
            Product savedProduct = productRepository.save(product);
            if(savedProduct != null){
                productStockRepository.save(new ProductStock(savedProduct,0L));
                return new Response(true,"保存成功!");
            }
            return new Response(false,"保存失败!");
        }
    }

}
