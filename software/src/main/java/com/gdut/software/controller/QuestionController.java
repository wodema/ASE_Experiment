package com.gdut.software.controller;

import com.alibaba.fastjson.JSON;
import com.gdut.software.entity.QueryInfo;
import com.gdut.software.entity.Question;

import com.gdut.software.service.QuestionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @PostMapping(value = "/addQuestion")
    @ResponseStatus(HttpStatus.CREATED)
    public String addQuestion(@RequestBody Map<String,Object> payload) throws IllegalAccessException {
        Logger log = LoggerFactory.getLogger(this.getClass());

        log.info( payload.toString());
        Question question = new Question();

        Field[] declaredFields = question.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {

            } else {
                field.setAccessible(true);
                if (field.getType().getName().equals("char")) {
                    field.set(question, (payload.get(field.getName())).toString().charAt(0));
                } else {
                    field.set(question, payload.get(field.getName()));
                }


            }
        }

        return questionService.addQuestion(question)== 1 ? "ok" : "error";

    }

    @DeleteMapping(value = "/deleteQuestionById/{id}")
    public String deleteQuestionById(@PathVariable int id) {
        int res = questionService.deleteQuestion(id);

        return res == 1 ? "ok" : "error";
    }


}
