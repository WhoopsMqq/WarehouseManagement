package com.whoops.analysis.controller;

import com.whoops.analysis.service.AnalysisService;
import com.whoops.vo.TableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AnalysisController {
    @Autowired
    private AnalysisService analysisService;

    @GetMapping("/dataAnalysis")
    public String dataAnalysis(){
        return "/page/dataAnalysis";
    }

    @GetMapping("/loadAnalysis")
    public TableData loadAnalysis(@RequestParam("startTime")String startTime,@RequestParam("endTime")String endTime,@RequestParam("kind")Integer kind){

        return new TableData();
    }


}
