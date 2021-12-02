package com.gdut.software.controller;

import com.alibaba.fastjson.JSON;

import com.gdut.software.service.PaperListService;
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
@RequestMapping("/paperList")
public class PaperListController {

    @Resource
    private PaperListService paperListService;
//
//    @PostMapping(value = "/getQuestions")
//    public String getQuestions(@RequestBody QueryInfo queryInfo) {
//        queryInfo.setPage(((queryInfo.getPage()) - 1) * queryInfo.getSize());
//        List<Question> paperListList = paperListService.getQuestions(queryInfo);
//        int count = paperListService.getCount(queryInfo);
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("number", count);
//        map.put("paperListList", paperListList);
//        return JSON.toJSONString(map);
//    }
}
