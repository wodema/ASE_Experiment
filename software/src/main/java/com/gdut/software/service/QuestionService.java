package com.gdut.software.service;

import com.gdut.software.entity.QueryInfo;
import com.gdut.software.entity.Question;
import com.gdut.software.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

        return questionMapper.updateQuestion(question);

    }

    public List<Question> getQuestions(QueryInfo queryInfo) {
        return questionMapper.getQuestions(queryInfo);
    }
    public int getCount(QueryInfo queryInfo) {
        return questionMapper.getCount(queryInfo);
    }

    public List<String> getQuestionKinds() {
        return questionMapper.getQuestionKinds();
    }
}
