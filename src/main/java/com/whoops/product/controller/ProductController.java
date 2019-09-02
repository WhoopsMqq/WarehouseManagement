package com.whoops.product.controller;

import com.whoops.product.pojo.Product;
import com.whoops.product.pojo.ProductStock;
import com.whoops.product.service.ProductService;
import com.whoops.stock.service.StockService;
import com.whoops.vo.Response;
import com.whoops.vo.TableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private StockService stockService;

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

//    @GetMapping("/addProduct")
//    public ResponseEntity<Response> addProduct(@RequestParam("id")Long id,@RequestParam("name")String name,@RequestParam("size")String size,@RequestParam("color")String color){
//        if (id != null && id != 0L){
//            Product oldProduct = productService.findProductById(id);
//            oldProduct.setName(name);
//            oldProduct.setSize(size);
//            oldProduct.setColor(color);
//            Product editedProduct = productService.saveProduct(oldProduct);
//            if(editedProduct != null){
//                return ResponseEntity.ok().body(new Response(true,"修改成功!"));
//            }
//            return  ResponseEntity.ok().body(new Response(false,"修改失败!"));
//        }else{
//            Product savedProduct = productService.saveProduct(new Product(name,size,color));
//            if(savedProduct != null){
//                stockService.saveProductStock(new ProductStock(savedProduct,0L));
//                return ResponseEntity.ok().body(new Response(true,"保存成功!"));
//            }
//            return  ResponseEntity.ok().body(new Response(false,"保存失败!"));
//        }
//    }

    @PostMapping(value="/addProduct", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Response> addProduct(@RequestBody Product product){
        if (product.getId() != null && product.getId() != 0L){
            Product oldProduct = productService.findProductById(product.getId());
            oldProduct.setName(product.getName());
            oldProduct.setSize(product.getSize());
            oldProduct.setColor(product.getColor());
            Product editedProduct = productService.saveProduct(oldProduct);
            if(editedProduct != null){
                return ResponseEntity.ok().body(new Response(true,"修改成功!"));
            }
            return  ResponseEntity.ok().body(new Response(false,"修改失败!"));
        }else{
            Product savedProduct = productService.saveProduct(new Product(product.getName(),product.getColor(),product.getSize()));
            if(savedProduct != null){
                stockService.saveProductStock(new ProductStock(savedProduct,0L));
                return ResponseEntity.ok().body(new Response(true,"保存成功!"));
            }
            return  ResponseEntity.ok().body(new Response(false,"保存失败!"));
        }
    }

}
