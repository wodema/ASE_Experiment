package com.gdut.software.service;

import com.gdut.software.entity.Question;
import com.gdut.software.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class QuestionService {
    @Resource
    private QuestionMapper questionMapper;

    public Question selectById(int id){
        return questionMapper.selectById(id);
    }

    public int addQuestion(Question question) {
        return questionMapper.addQuestion(question);
    }

    public int deleteQuestion(int id) {
        return questionMapper.deleteQuestion(id);
    }

    public int updateQuestion(Question question) {
        int id = question.getId();
        if (selectById(id) != null) {
            deleteQuestion(id);
        }
        return addQuestion(question);
    }
}
