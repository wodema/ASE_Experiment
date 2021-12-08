package com.gdut.software.service;

import com.gdut.software.entity.PaperList;
import com.gdut.software.entity.QueryInfo;
import com.gdut.software.entity.Question;
import com.gdut.software.mapper.PaperListMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
    public List<Question> getPaper(Integer id){
        return paperListMapper.getPaper(id);
    }
    public int updatePaperList(List<Map<String, Object>> identityList){
        return paperListMapper.updatePaperList("paper_show", identityList,identityList.get(0));
    }

    public int deletePaper(Integer id){
        return paperListMapper.deletePaper(id);
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

    public int deletePaperListById(int id) {
        return paperListMapper.deletePaperListById(id);
    }

    public List<String> getPaperKinds(){
        return paperListMapper.getPaperKinds();
    }
}

