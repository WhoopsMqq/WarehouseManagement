package com.whoops.commons;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    @RequestMapping
    public String index(){
        return "index";
    }
    @RequestMapping("/main")
    public ModelAndView main(Model model){
        return new ModelAndView("/page/main","model",model);
    }

}
