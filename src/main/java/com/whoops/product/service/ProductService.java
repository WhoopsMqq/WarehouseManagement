package com.whoops.product.service;

import com.whoops.product.pojo.Product;
import com.whoops.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> loadAllProduct(){
        return productRepository.findAll();
    }
}
