package com.gdut.software.controller;

import com.alibaba.fastjson.JSON;
import com.gdut.software.entity.Question;
import com.gdut.software.mapper.QuestionMapper;
import com.gdut.software.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Resource
    private QuestionService questionService;

    @GetMapping(value = "/findQuestionById/{id}")
    public String findQuestionById(@PathVariable int id) {
        Question question = questionService.selectById(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("question", question);
        return JSON.toJSONString(map);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question) == 1 ? "ok" : "error";
    }

    @DeleteMapping(value = "/{id}")
    public String deleteQuestionById(@PathVariable int id) {
        int res = questionService.deleteQuestion(id);

        return res == 1 ? "ok" : "error";
    }


}
