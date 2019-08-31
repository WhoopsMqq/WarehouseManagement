package com.whoops.stock.controller;

import com.whoops.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StockController {
    @Autowired
    private StockService stockService;
}
