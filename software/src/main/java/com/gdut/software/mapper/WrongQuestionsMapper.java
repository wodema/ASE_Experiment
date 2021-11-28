package com.gdut.software.mapper;

import com.gdut.software.entity.QueryInfo;
import com.gdut.software.entity.WrongQuestions;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper 
public interface WrongQuestionsMapper {

    public int addWrongQuestion(WrongQuestions wrongQuestions);

    public List<WrongQuestions> selectQuestions(QueryInfo queryInfo);

    public int getCount(QueryInfo queryInfo);

    public List<String> getKinds(int id);

    public int deleteById(int id);
}
