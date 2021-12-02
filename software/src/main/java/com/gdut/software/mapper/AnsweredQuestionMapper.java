package com.gdut.software.mapper;

import com.gdut.software.entity.AnsweredQuestion;
import com.gdut.software.entity.QueryInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper 
public interface AnsweredQuestionMapper {

    public int addAnsweredQuestion(AnsweredQuestion AnsweredQuestion);

    public List<AnsweredQuestion> selectWrongQuestions(QueryInfo queryInfo);

    public int getCount(QueryInfo queryInfo);

    public List<String> getKinds(int id);

    public int deleteBySidAndQid(int sid, int qid);

    public List<HashMap> getAnalyseInformation(int id);
}
