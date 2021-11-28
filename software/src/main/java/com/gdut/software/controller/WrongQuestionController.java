package com.gdut.software.controller;

import com.alibaba.fastjson.JSON;
import com.gdut.software.entity.QueryInfo;
import com.gdut.software.entity.Question;
import com.gdut.software.entity.WrongQuestions;
import com.gdut.software.mapper.QuestionMapper;
import com.gdut.software.service.WrongQuestionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
public class WrongQuestionController {
    @Resource
    private WrongQuestionService wrongQuestionService;
    @Resource
    private QuestionMapper questionMapper;

    @RequestMapping(value = "/getWrongQuestions", method = RequestMethod.POST)
    public String getWrongQuestions(@RequestBody QueryInfo queryInfo){
        queryInfo.setPage((queryInfo.getPage() - 1) * queryInfo.getSize());
        List<WrongQuestions> qList = wrongQuestionService.selectQuestions(queryInfo);
        int count = wrongQuestionService.getCount(queryInfo);
        HashMap<String, Object> map = new HashMap<>();
        map.put("number", count);
        map.put("questionList", qList);
        return JSON.toJSONString(map);
    }

    @RequestMapping("/getWrongQuestionsKinds/{id}")
    public String getWrongQuestionsKinds(@PathVariable int id){
        List<String> kList = wrongQuestionService.getKinds(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("kinds", kList);
        return JSON.toJSONString(map);
    }

    @RequestMapping("/getQuestionInf/{id}")
    public String getQuestionInf(@PathVariable int id){
        Question question = questionMapper.selectById(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("questionInf", question);
        return JSON.toJSONString(map);
    }

    @RequestMapping(value = "/deleteWrongQuestion/{id}", method = RequestMethod.DELETE)
    public String deleteWrongQuestion(@PathVariable int id){
        int res = wrongQuestionService.deleteById(id);
        if(res == 1)
            return "ok";
        else
            return "error";
    }

    @RequestMapping("/getAnalyseInformation/{id}")
    public String getAnalyseInformation(@PathVariable int id){
        List<HashMap> lMap = wrongQuestionService.getAnalyseInformation(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("analyseInfo", lMap);
        return JSON.toJSONString(map);
    }

}
