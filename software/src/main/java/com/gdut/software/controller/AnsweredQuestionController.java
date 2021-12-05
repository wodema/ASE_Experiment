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
@RequestMapping("/answeredQuestions")
public class AnsweredQuestionController {
    @Resource
    private AnsweredQuestionService answeredQuestionService;
    @Resource
    private QuestionMapper questionMapper;

    @RequestMapping(value = "/getWrongQuestions", method = RequestMethod.POST)
    public String getWrongQuestions(@RequestBody QueryInfo queryInfo){
        queryInfo.setPage((queryInfo.getPage() - 1) * queryInfo.getSize());
        List<AnsweredQuestion> qList = answeredQuestionService.selectWrongQuestions(queryInfo);
        int count = qList.size();
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

    @RequestMapping("/getStudentQuestionInfo/{sid}")
    public String getStudentQuestionInfo(@PathVariable int sid){
        List<HashMap> lMap = answeredQuestionService.getStudentQuestionInfo(sid);
        HashMap<String, Object> map = new HashMap<>();
        map.put("analyseInfo", lMap);
        return JSON.toJSONString(map);
    }

    @RequestMapping("/getQuestionAnalyseInfo/{qid}")
    public String getQuestionAnalyseInfo(@PathVariable int qid){
        List<HashMap> qMap = answeredQuestionService.getQuestionAnalyseInfo(qid);
        HashMap<String, Object> map = new HashMap<>();
        map.put("questionInfo", qMap);
        return JSON.toJSONString(map);
    }


}
