package com.whoops.accessories.controller;

import com.whoops.accessories.pojo.Accessories;
import com.whoops.accessories.service.AccessoriesService;
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
public class AccessoriesController {

    @Autowired
    private AccessoriesService accessoriesService;

    @GetMapping("/accessoriesList")
    public String accessoriesList(){
        return "page/accessories/accessoriesList";
    }

    @GetMapping("/accessoriesListJson")
    @ResponseBody
    public TableData accessoriesListJson(){
        List<Accessories> accessoriesList = accessoriesService.loadAllAccessories();
        return new TableData(0,"",accessoriesList.size(),accessoriesList);
    }

    @GetMapping("/accessoriesAdd")
    public String accessoriesAdd(){
        return "page/accessories/accessoriesAdd";
    }


    @PostMapping(value="/accessoriesAdd", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<Response> addAccessories(@RequestBody Accessories accessories){
        return ResponseEntity.ok().body(accessoriesService.saveAccessories(accessories));
    }

}
