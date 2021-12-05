package com.gdut.software.mapper;

import com.gdut.software.entity.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaperQuestionMapper {
    List<Question> findQuestionsByPaperId(int id);

//    int appendPaperWithQuestions(int[] questionId);

    int addPaperQuestionRelationship(int paper_id, int question_id);
}
