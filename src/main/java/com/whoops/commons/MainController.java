package com.whoops.commons;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @RequestMapping
    public String index(){
        return "index-1";
    }

    @RequestMapping("/main")
    public ModelAndView main(Model model){
        return new ModelAndView("/page/main","model",model);
    }

}
