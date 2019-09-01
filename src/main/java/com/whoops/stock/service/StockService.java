package com.whoops.stock.service;

import com.whoops.accessories.pojo.AccessoriesStock;
import com.whoops.accessories.repository.AccessoriesStockRepository;
import com.whoops.material.pojo.MaterialStock;
import com.whoops.material.repository.MaterialStockRepository;
import com.whoops.product.pojo.ProductStock;
import com.whoops.product.repository.ProductStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {
    @Autowired
    private ProductStockRepository productStockRepository;

    @Autowired
    private MaterialStockRepository materialStockRepository;

    @Autowired
    private AccessoriesStockRepository accessoriesStockRepository;

    public List<ProductStock> getProductStock(){
        return productStockRepository.findAll();
    }

    public List<MaterialStock> getMaterialStock(){
        return materialStockRepository.findAll();
    }

    public List<AccessoriesStock> getAccessoriesStock(){
        return accessoriesStockRepository.findAll();
    }
}
