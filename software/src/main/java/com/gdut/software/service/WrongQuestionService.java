package com.gdut.software.service;

import com.gdut.software.entity.QueryInfo;
import com.gdut.software.entity.WrongQuestions;
import com.gdut.software.mapper.WrongQuestionsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class WrongQuestionService {
    @Resource
    private WrongQuestionsMapper wrongQuestionsMapper;

    public List<WrongQuestions> selectQuestions(QueryInfo queryInfo){
        return wrongQuestionsMapper.selectQuestions(queryInfo);
    }

    public int getCount(QueryInfo queryInfo){
        return wrongQuestionsMapper.getCount(queryInfo);
    }

    public List<String> getKinds(int id){
        return wrongQuestionsMapper.getKinds(id);
    }

    public int deleteById(int id){ return wrongQuestionsMapper.deleteById(id); }

    public List<HashMap> getAnalyseInformation(int id){
        return wrongQuestionsMapper.getAnalyseInformation(id);
    }
}
