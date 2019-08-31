package com.whoops.stock.service;

import com.whoops.accessories.repository.AccessoriesStockRepository;
import com.whoops.material.repository.MaterialStockRepository;
import com.whoops.product.repository.ProductStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {
    @Autowired
    private ProductStockRepository productStockRepository;

    @Autowired
    private MaterialStockRepository materialStockRepository;

    @Autowired
    private AccessoriesStockRepository accessoriesStockRepository;
}
