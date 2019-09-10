package com.whoops.analysis.controller;

import com.whoops.analysis.pojo.Analysis;
import com.whoops.analysis.service.AnalysisService;
import com.whoops.vo.TableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AnalysisController {
    @Autowired
    private AnalysisService analysisService;

    @GetMapping("/dataAnalysis")
    public String dataAnalysis(){
        return "page/dataAnalysis";
    }

    @GetMapping("/loadAnalysis")
    @ResponseBody
    public TableData loadAnalysis(@RequestParam(name = "startTime",required = false)String startTime,
                                  @RequestParam(name = "endTime",required = false)String endTime,
                                  @RequestParam(name = "kind",required = false)Integer kind){
        List<Analysis> analysisList = analysisService.loadAnalysis(startTime,endTime,kind);
        return new TableData(0,"",analysisList.size(),analysisList);
    }
}
