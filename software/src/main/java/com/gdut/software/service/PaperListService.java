package com.gdut.software.service;

import com.gdut.software.entity.PaperList;
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

}
