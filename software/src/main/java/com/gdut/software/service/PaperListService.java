package com.gdut.software.service;

import com.gdut.software.entity.PaperList;
import com.gdut.software.entity.QueryInfo;
import com.gdut.software.mapper.PaperListMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PaperListService {
    @Resource
    private PaperListMapper paperListMapper;

//    public List<PaperList> getPaperList(int id){
//        return paperListMapper.getPaperList(id);
//    }
    public List<PaperList> getPaperList(){
    return paperListMapper.getPaperList();
}


    public int addPaperList(PaperList paperList) {
        return paperListMapper.addPaperList(paperList);
    }

    public PaperList getPaperListById(int id) {
        return paperListMapper.getPaperListById(id);
    }

    public List<PaperList> getPaperOfAnalyse(QueryInfo queryInfo){
        return paperListMapper.getPaperOfAnalyse(queryInfo);
    }
}

