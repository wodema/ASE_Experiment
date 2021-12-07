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
import java.util.Map;

@RestController
@RequestMapping("/answeredQuestions")
public class AnsweredQuestionController {
    @Resource
    private AnsweredQuestionService answeredQuestionService;
    @Resource
    private QuestionMapper questionMapper;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insertAnsweredQuestions(@RequestBody Map<String, Object> para){
//    public String insertAnsweredQuestions(@RequestBody List<Question> questionList,@RequestBody List<String> answerList,@RequestBody int sid){
//        para.get("questionList");
//        para.get("answerList");
//        para.get("sid");
//        System.out.println(questionList); System.out.println(sid); System.out.println(answerList);

//        System.out.println(para);
//        System.out.println(para.get("sid"));
//        System.out.println(para.get("answerList").getClass());
//        System.out.println(((List)para.get("answerList")).get(0).getClass());
//        System.out.println(((List)para.get("idList")).get(0).getClass());
////        System.out.println(((List)para.get("questionList")).get(0).getClass());

        int affectedRecordNumber=answeredQuestionService.insertAnsweredQuestions(Integer.parseInt(para.get("sid").toString()),(List)para.get("answerList"),(List)para.get("idList"));
        HashMap<String, Object> map = new HashMap<>();
        map.put("affectedRecordNumber", affectedRecordNumber);
        return JSON.toJSONString(map);
    }

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
