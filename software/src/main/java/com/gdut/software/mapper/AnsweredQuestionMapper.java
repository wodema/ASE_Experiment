package com.gdut.software.mapper;

import com.gdut.software.entity.AnsweredQuestion;
import com.gdut.software.entity.QueryInfo;
import com.gdut.software.entity.WrongQuestion;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper 
public interface AnsweredQuestionMapper {

    public int addAnsweredQuestion(AnsweredQuestion AnsweredQuestion);

    public List<WrongQuestion> selectWrongQuestions(QueryInfo queryInfo);

    public List<String> getKinds(int id);

    public int deleteBySidAndQid(int sid, int qid);

    public List<HashMap> getStudentQuestionInfo(int sid);

    public List<HashMap> getQuestionAnalyseInfo(int qid);
}
