package com.gdut.software.controller;

import com.alibaba.fastjson.JSON;

import com.gdut.software.entity.PaperList;
import com.gdut.software.entity.Question;
import com.gdut.software.service.PaperListService;
import com.gdut.software.service.PaperQuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/paperList")
public class PaperListController {

    @Resource
    private PaperListService paperListService;

    @Resource
    private PaperQuestionService paperQuestionService;

    @PostMapping(value = "/getPaperList")
//    public String getPaperList(@RequestBody QueryInfo queryInfo) {
    public String getPaperList() {
        List<PaperList> paperListList = paperListService.getPaperList();
        HashMap<String, Object> map = new HashMap<>();
        map.put("paperList", paperListList);
        return JSON.toJSONString(map);
    }

    @PostMapping(value = "/addPaperWithQuestions")
    public String addPaper(@RequestBody Map<String, Object> payload) {

        Logger log = LoggerFactory.getLogger(this.getClass());

        log.info(payload.toString());
        ArrayList<Integer> questionIdArray = (ArrayList<Integer>) payload.get("candidateQuestionsForExam");
        PaperList paperList = new PaperList();
        Object paperObj = payload.get("paperList");
        log.info(paperObj.getClass().toString());
        LinkedHashMap<String, String> paperMap = (LinkedHashMap<String, String>) paperObj;
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        paperList.setTotal_score(questionIdArray.size()*10);
        paperList.setPaper_date(timestamp);
        paperList.setPaper_name(paperMap.get("paper_name"));
        paperList.setTotal_time(String.valueOf(paperMap.get("total_time")));

        boolean res = paperListService.addPaperList(paperList) == 1;
        int paper_id = paperList.getPaper_id();
        for (Integer question_id : questionIdArray) {
            res =  res && paperQuestionService.addPaperQuestionRelationship(paper_id, question_id) == 1;
        }

        return res ? "ok" : "error";
    }

    @GetMapping(value = "/getPaperListById/{id}")
    public String getPaperListById(@PathVariable int id) {
        return JSON.toJSONString(paperListService.getPaperListById(id));
    }

    @GetMapping(value = "/getQuestionsByPaperId/{id}")
    public String getQuestionsByPaperId(@PathVariable int id) {
        Logger log = LoggerFactory.getLogger(this.getClass());

        log.info(String.valueOf(id));
        return JSON.toJSONString(paperQuestionService.findQuestionsByPaperId(id));
    }
}
