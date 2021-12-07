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

    public int insertScore(int sid,int paper_id,int score){
        return scoreMapper.insertScore(sid, paper_id, score);
    }

    public List<HashMap> getStudentScoreInfo(int id){
        return scoreMapper.getStudentScoreInfo(id);
    }

    public List<HashMap> getPaperAnswerInfo(int id){
        return scoreMapper.getPaperAnswerInfo(id);
    }

    public List<HashMap> getPaperStudentInfo(int id, int score){
        return scoreMapper.getPaperStudentInfo(id, score);
    }
}
