package com.gdut.software.service;

import com.gdut.software.mapper.ScoreMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Transactional
@Service
public class ScoreService {
    @Resource
    private ScoreMapper scoreMapper;

    public List<HashMap> getStudentScoreInfo(int id){
        return scoreMapper.getStudentScoreInfo(id);
    }
}
