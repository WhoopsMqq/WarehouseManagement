package com.whoops.product.controller;

import com.whoops.product.pojo.Product;
import com.whoops.product.pojo.ProductInOut;
import com.whoops.product.service.ProductInOutService;
import com.whoops.vo.TableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProductInOutController {

    @Autowired
    private ProductInOutService productInOutService;

    @GetMapping("/productInOutList")
    public String productInOutList(){
        return "/page/product/productInOutList";
    }

    @GetMapping("/productInOutListJson")
    @ResponseBody
    public TableData productInOutListJson(){
        List<ProductInOut> productInOutList = productInOutService.loadAllProductInOut();
        return new TableData(0,"",productInOutList.size(),productInOutList);
    }
}
