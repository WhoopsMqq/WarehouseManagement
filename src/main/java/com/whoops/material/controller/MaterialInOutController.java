package com.whoops.material.controller;

import com.whoops.material.pojo.MaterialInOut;
import com.whoops.material.service.MaterialInOutService;
import com.whoops.material.service.MaterialService;
import com.whoops.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class MaterialInOutController {
    @Autowired
    private MaterialInOutService materialInOutService;

    @Autowired
    private MaterialService materialService;

    @GetMapping("/materialInOutList")
    public String materialInOutList(Model model){
        List<MaterialInOut> materialInOutList = materialInOutService.loadAllMaterialInOut();
        model.addAttribute("materialInOutList",materialInOutList);
        return "page/material/materialInOutList";
    }

    @GetMapping("/materialInOutAdd")
    public String materialInOutAdd(Model model){
        model.addAttribute("materialList",materialService.loadAllMaterial());
        return "page/material/materialInOutAdd";
    }

    @PostMapping(value="/materialInOutAdd", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Response> addMaterialInOut(@RequestBody MaterialInOut materialInOut){
        return ResponseEntity.ok().body(materialInOutService.saveMaterialInOut(materialInOut));
    }
}
