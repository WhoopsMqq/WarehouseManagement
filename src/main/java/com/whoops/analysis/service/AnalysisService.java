package com.whoops.analysis.service;

import com.whoops.accessories.repository.AccessoriesInOutRepository;
import com.whoops.material.repository.MaterialInOutRepository;
import com.whoops.product.repository.ProductInOutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalysisService {
    @Autowired
    private ProductInOutRepository productInOutRepository;
    @Autowired
    private MaterialInOutRepository materialInOutRepository;
    @Autowired
    private AccessoriesInOutRepository accessoriesInOutRepository;






}
