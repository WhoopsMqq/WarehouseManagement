package com.whoops.product.controller;

import com.whoops.product.pojo.Product;
import com.whoops.product.pojo.ProductInOut;
import com.whoops.product.service.ProductInOutService;
import com.whoops.product.service.ProductService;
import com.whoops.vo.Response;
import com.whoops.vo.TableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProductInOutController {

    @Autowired
    private ProductInOutService productInOutService;
    @Autowired
    private ProductService productService;

    @GetMapping("/productInOutList")
    public String productInOutList(Model model){
        List<ProductInOut> productInOutList = productInOutService.loadAllProductInOut();
        model.addAttribute("productInOutList",productInOutList);
        return "/page/product/productInOutList";
    }

    @GetMapping("/productInOutAdd")
    public String productInOutAdd(Model model){
        model.addAttribute("productList",productService.loadAllProduct());
        return "/page/product/productInOutAdd";
    }

    @PostMapping(value="/productInOutAdd", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Response> addProductInOut(@RequestBody ProductInOut productInOut){
        return ResponseEntity.ok().body(productInOutService.saveProductInOut(productInOut));
    }

}
