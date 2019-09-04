package com.whoops.product.service;

import com.whoops.account.pojo.User;
import com.whoops.commons.CurrentUser;
import com.whoops.product.pojo.Product;
import com.whoops.product.pojo.ProductInOut;
import com.whoops.product.pojo.ProductStock;
import com.whoops.product.repository.ProductInOutRepository;
import com.whoops.product.repository.ProductRepository;
import com.whoops.product.repository.ProductStockRepository;
import com.whoops.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class ProductInOutService {

    @Autowired
    private ProductInOutRepository productInOutRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductStockRepository productStockRepository;

    public List<ProductInOut> loadAllProductInOut(){
        List<ProductInOut> productInOutList = productInOutRepository.findAll();
        productInOutList.sort(new Comparator<ProductInOut>() {
            @Override
            public int compare(ProductInOut p1, ProductInOut p2) {
                if(p1.getCreateTime().after(p2.getCreateTime())){
                    return -1;
                }else if(p1.getCreateTime().before(p2.getCreateTime())){
                    return 1;
                }
                return 0;
            }
        });
        return productInOutList;
    }

    public Response saveProductInOut(ProductInOut productInOut) {
//        User currentUser = CurrentUser.getUser();
//        if(currentUser == null){
//            return new Response(false,"请先登录,再进行操作!","/login");
//        }

        Product product = productRepository.getOne(productInOut.getProduct().getId());
        ProductStock productStock = productStockRepository.findByProduct(product);
        if(productInOut.getType() == 1 && productInOut.getNumber() > productStock.getNumber()){
            return new Response(false,"对不起,库存不足,请重新核对出库数量!");
        }else if(productInOut.getType() == 1 && productInOut.getNumber() <= productStock.getNumber()){
            productStock.setNumber(productStock.getNumber() - productInOut.getNumber());
            productStockRepository.save(productStock);
        }else{
            productStock.setNumber(productStock.getNumber() + productInOut.getNumber());
            productStockRepository.save(productStock);
        }

        productInOut.setProduct(product);
//        productInOut.setUser_id(currentUser.getId());
//        productInOut.setUsername(currentUser.getUsername());
        productInOut.setType(productInOut.getType());
        ProductInOut savedProductInOut = productInOutRepository.save(productInOut);
        if(savedProductInOut != null){
            return new Response(true,"保存成功!");
        }
        return new Response(false,"保存失败!");
    }
}
