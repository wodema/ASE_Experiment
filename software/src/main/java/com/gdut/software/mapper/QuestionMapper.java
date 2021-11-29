package com.gdut.software.mapper;

import com.gdut.software.entity.Question;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
    Question selectById(int id);

    int addQuestion(Question question);

    int deleteQuestion(int id);

    int updateQuestion(Question question);
}
