package com.gdut.software.controller;

import com.alibaba.fastjson.JSON;
import com.gdut.software.entity.AnsweredQuestion;
import com.gdut.software.entity.QueryInfo;
import com.gdut.software.entity.Question;
import com.gdut.software.mapper.QuestionMapper;
import com.gdut.software.service.AnsweredQuestionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
public class AnsweredQuestionController {
    @Resource
    private AnsweredQuestionService answeredQuestionService;
    @Resource
    private QuestionMapper questionMapper;

    @RequestMapping(value = "/getWrongQuestions", method = RequestMethod.POST)
    public String getWrongQuestions(@RequestBody QueryInfo queryInfo){
        queryInfo.setPage((queryInfo.getPage() - 1) * queryInfo.getSize());
        List<AnsweredQuestion> qList = answeredQuestionService.selectWrongQuestions(queryInfo);
        int count = answeredQuestionService.getCount(queryInfo);
        HashMap<String, Object> map = new HashMap<>();
        map.put("number", count);
        map.put("questionList", qList);
        return JSON.toJSONString(map);
    }

    @RequestMapping("/getWrongQuestionsKinds/{id}")
    public String getWrongQuestionsKinds(@PathVariable int id){
        List<String> kList = answeredQuestionService.getKinds(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("kinds", kList);
        return JSON.toJSONString(map);
    }

    @RequestMapping("/getQuestionInfo/{id}")
    public String getQuestionInf(@PathVariable int id){
        Question question = questionMapper.selectById(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("questionInfo", question);
        return JSON.toJSONString(map);
    }

    @RequestMapping(value = "/deleteWrongQuestion", method = RequestMethod.DELETE)
    public String deleteWrongQuestion(@Param("sid") int sid, @Param("qid") int qid){
        int res = answeredQuestionService.deleteBySidAndQid(sid, qid);
        if(res != 0)
            return "ok";
        else
            return "error";
    }

    @RequestMapping("/getAnalyseInformation/{id}")
    public String getAnalyseInformation(@PathVariable int id){
        List<HashMap> lMap = answeredQuestionService.getAnalyseInformation(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("analyseInfo", lMap);
        return JSON.toJSONString(map);
    }

}
