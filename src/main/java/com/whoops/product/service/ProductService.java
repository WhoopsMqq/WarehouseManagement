package com.whoops.product.service;

import com.whoops.product.pojo.Product;
import com.whoops.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> loadAllProduct(){
        return productRepository.findAll();
    }

    public Product findProductById(Long id){
        return productRepository.getOne(id);
    }

    public Product saveProduct(Product product){
        Product savedProduct = null;
        try {
            savedProduct = productRepository.save(product);
        }catch (Exception e){
            return savedProduct;
        }
        return savedProduct;
    }
}
