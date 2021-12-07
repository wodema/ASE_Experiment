package com.gdut.software.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface ScoreMapper {

    public int insertScore(@Param("sid")int sid,@Param("paper_id")int paper_id,@Param("score")int score);

    public List<HashMap> getStudentScoreInfo(int id);

    public List<HashMap> getPaperAnswerInfo(int id);

    public List<HashMap> getPaperStudentInfo(int id, int score);
}
