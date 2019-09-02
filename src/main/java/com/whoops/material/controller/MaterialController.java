package com.whoops.material.controller;

import com.whoops.material.pojo.Material;
import com.whoops.material.service.MaterialService;
import com.whoops.product.pojo.Product;
import com.whoops.vo.Response;
import com.whoops.vo.TableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @GetMapping("/materialList")
    public String materialList(){
        return "/page/material/materialList";
    }

    @GetMapping("/materialListJson")
    @ResponseBody
    public TableData materialListJson(){
        List<Material> materialList = materialService.loadAllMaterial();
        return new TableData(0,"",materialList.size(),materialList);
    }

    @GetMapping("/materialAdd")
    public String productAdd(){
        return "/page/material/materialAdd";
    }


    @PostMapping(value="/materialAdd", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<Response> addMaterial(@RequestBody Material material){
        return ResponseEntity.ok().body(materialService.saveMaterial(material));
    }

}
