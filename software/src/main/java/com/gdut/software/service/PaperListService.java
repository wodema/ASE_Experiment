package com.gdut.software.service;

import com.gdut.software.mapper.PaperListMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PaperListService {
    @Resource
    private PaperListMapper paperListMapper;

//    public Question selectById(int id){
//        return questionMapper.selectById(id);
//    }

}
