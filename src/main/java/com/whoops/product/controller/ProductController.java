package com.whoops.product.controller;

import com.whoops.product.pojo.Product;
import com.whoops.product.service.ProductService;
import com.whoops.vo.TableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/productList")
    public String productList(){
        return "/page/product/productList";
    }

    @GetMapping("/productListJson")
    @ResponseBody
    public TableData productListJson(){
        List<Product> productList = productService.loadAllProduct();
        return new TableData(0,"",productList.size(),productList);
    }

    @GetMapping("/productAdd")
    public String productAdd(){
        return "/page/product/productAdd";
    }
}
