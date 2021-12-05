package com.gdut.software.controller;

import com.alibaba.fastjson.JSON;
import com.gdut.software.service.ScoreService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/score")
public class ScoreController {
    @Resource
    private ScoreService scoreService;

    @RequestMapping("/getStudentScoreInfo/{id}")
    public String getScoreInformation(@PathVariable int id){
        List<HashMap> lMap = scoreService.getStudentScoreInfo(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("ScoreInfo", lMap);
        return JSON.toJSONString(map);
    }

    @RequestMapping(value = "/getPaperAnswerInfo/{id}")
    public String getPaperAnswerInfo(@PathVariable int id){
        List<HashMap> lMap = scoreService.getPaperAnswerInfo(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("paperInfo", lMap);
        return JSON.toJSONString(map);
    }

    @RequestMapping(value = "/getPaperStudentInfo/")
    public String getPaperStudentInfo(@RequestParam("id") int id, @RequestParam("score") int score){
        List<HashMap> lMap = scoreService.getPaperStudentInfo(id, score);
        HashMap<String, Object> map = new HashMap<>();
        map.put("paperStudentInfo", lMap);
        return JSON.toJSONString(map);
    }
}
