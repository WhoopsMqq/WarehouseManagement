package com.whoops.stock.controller;

import com.whoops.accessories.pojo.AccessoriesStock;
import com.whoops.material.pojo.MaterialStock;
import com.whoops.product.pojo.ProductStock;
import com.whoops.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StockController {
    @Autowired
    private StockService stockService;

    @GetMapping("/main")
    public String main(Model model){
        List<ProductStock> productStockList = stockService.getProductStock();
        List<MaterialStock> materialStockList = stockService.getMaterialStock();
        List<AccessoriesStock> accessoriesStockList = stockService.getAccessoriesStock();
        model.addAttribute("productStockList",productStockList);
        model.addAttribute("materialStockList",materialStockList);
        model.addAttribute("accessoriesStockList",accessoriesStockList);
        return "page/main";
    }


}
