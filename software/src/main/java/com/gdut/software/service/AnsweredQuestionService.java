package com.gdut.software.service;

import com.gdut.software.entity.AnsweredQuestion;
import com.gdut.software.entity.QueryInfo;
import com.gdut.software.mapper.AnsweredQuestionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Transactional
@Service
public class AnsweredQuestionService {
    @Resource
    private AnsweredQuestionMapper answeredQuestionMapper;

    public List<AnsweredQuestion> selectWrongQuestions(QueryInfo queryInfo){
        return answeredQuestionMapper.selectWrongQuestions(queryInfo);
    }

    public int getCount(QueryInfo queryInfo){
        return answeredQuestionMapper.getCount(queryInfo);
    }

    public List<String> getKinds(int id){
        return answeredQuestionMapper.getKinds(id);
    }

    public int deleteBySidAndQid(int sid, int qid){ return answeredQuestionMapper.deleteBySidAndQid(sid, qid); }

    public List<HashMap> getStudentQuestionInfo(int sid){
        return answeredQuestionMapper.getStudentQuestionInfo(sid);
    }
}
