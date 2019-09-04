package com.whoops.accessories.controller;

import com.whoops.accessories.pojo.AccessoriesInOut;
import com.whoops.accessories.service.AccessoriesInOutService;
import com.whoops.accessories.service.AccessoriesService;
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
public class AccessoriesInOutController {
    @Autowired
    private AccessoriesInOutService accessoriesInOutService;
    @Autowired
    private AccessoriesService accessoriesService;

    @GetMapping("/accessoriesInOutList")
    public String accessoriesInOutList(Model model){
        List<AccessoriesInOut> accessoriesInOutList = accessoriesInOutService.loadAllAccessoriesInOut();
        model.addAttribute("accessoriesInOutList",accessoriesInOutList);
        return "/page/accessories/accessoriesInOutList";
    }

    @GetMapping("/accessoriesInOutAdd")
    public String accessoriesInOutAdd(Model model){
        model.addAttribute("accessoriesList",accessoriesService.loadAllAccessories());
        return "/page/accessories/accessoriesInOutAdd";
    }

    @PostMapping(value="/accessoriesInOutAdd", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Response> addAccessoriesInOut(@RequestBody AccessoriesInOut accessoriesInOut){
        return ResponseEntity.ok().body(accessoriesInOutService.saveAccessoriesInOut(accessoriesInOut));
    }
}
