package com.whoops.product.service;

import com.whoops.product.pojo.ProductInOut;
import com.whoops.product.repository.ProductInOutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ProductInOutService {

    @Autowired
    private ProductInOutRepository productInOutRepository;

    public List<ProductInOut> loadAllProductInOut(){
        List<ProductInOut> productInOutList = productInOutRepository.findAll();
        productInOutList.sort(new Comparator<ProductInOut>() {
            @Override
            public int compare(ProductInOut p1, ProductInOut p2) {
                if(p1.getCreateTime().after(p2.getCreateTime())){
                    return 1;
                }else if(p1.getCreateTime().before(p2.getCreateTime())){
                    return -1;
                }
                return 0;
            }
        });
        return productInOutList;
    }

}
