package com.gdut.software.controller;

import com.alibaba.fastjson.JSON;
import com.gdut.software.entity.QueryInfo;
import com.gdut.software.entity.Question;
import com.gdut.software.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Resource
    private QuestionService questionService;

    @PostMapping(value = "/getQuestions")
    public String getQuestions(@RequestBody QueryInfo queryInfo) {

        queryInfo.setPage(((queryInfo.getPage()) - 1) * queryInfo.getSize());
        List<Question> questionList = questionService.getQuestions(queryInfo);
        int count = questionService.getCount(queryInfo);
        HashMap<String, Object> map = new HashMap<>();
        map.put("number", count);
        map.put("questionList", questionList);
        return JSON.toJSONString(map);
    }


    @GetMapping(value = "/findQuestionById/{id}")
    public String findQuestionById(@PathVariable int id) {
        Question question = questionService.selectById(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("question", question);
        return JSON.toJSONString(map);
    }

    @GetMapping(value = "/getQuestionKinds")
    public String getQuestionKinds() {

        List<String> kindsList = questionService.getQuestionKinds();
        HashMap<String, Object> map = new HashMap<>();
        map.put("kinds", kindsList);
        return JSON.toJSONString(map);
    }

    @DeleteMapping(value = "/deleteQuestionById/{id}")
    public String deleteQuestionById(@PathVariable int id) {
        int res = questionService.deleteQuestion(id);

        return res == 1 ? "ok" : "error";
    }

    @PostMapping(value = "/addQuestion")
    @ResponseStatus(HttpStatus.CREATED)
    public String addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question) == 1 ? "ok" : "error";
    }

    @PutMapping(value = "/updateQuestion")
    public String updateQuestion(@RequestBody Question question) {
        return questionService.updateQuestion(question) == 1 ? "ok" : "error";
    }


}
