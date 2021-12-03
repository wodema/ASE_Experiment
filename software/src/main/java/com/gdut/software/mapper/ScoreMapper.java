package com.gdut.software.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface ScoreMapper {

    public List<HashMap> getStudentScoreInfo(int id);
}
