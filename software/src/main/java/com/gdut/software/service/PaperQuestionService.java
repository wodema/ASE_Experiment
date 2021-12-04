package com.gdut.software.service;

import com.gdut.software.entity.Question;
import com.gdut.software.mapper.PaperQuestionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PaperQuestionService {
    @Resource
    private PaperQuestionMapper paperQuestionMapper;

    public List<Question> findQuestionsByPaperId(int id) {
        return paperQuestionMapper.findQuestionsByPaperId(id);
    }

    public int addPaperQuestionRelationship(int paper_id, int question_id) {
        return paperQuestionMapper.addPaperQuestionRelationship(paper_id, question_id);
    }



//    public int appendPaperWithQuestions(int[] questionId) {
//        return paperQuestionMapper.appendPaperWithQuestions(questionId);
//    }
}
