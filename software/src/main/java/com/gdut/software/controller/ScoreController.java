package com.gdut.software.controller;

import com.alibaba.fastjson.JSON;
import com.gdut.software.service.ScoreService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/score")
public class ScoreController {
    @Resource
    private ScoreService scoreService;
    @RequestMapping(value = "/insertScore", method = RequestMethod.POST)
    public String insertScore(@RequestBody Map<String, Object> para){
        int  affectedRecordNumber = scoreService.insertScore(Integer.parseInt(para.get("sid").toString()),Integer.parseInt(para.get("paper_id").toString()),Integer.parseInt(para.get("score").toString()));
        HashMap<String, Object> map = new HashMap<>();
        map.put("affectedRecordNumber", affectedRecordNumber);
        return JSON.toJSONString(map);
    }
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
