package com.gdut.software.service;

import com.gdut.software.entity.AnsweredQuestion;
import com.gdut.software.entity.QueryInfo;
import com.gdut.software.mapper.AnsweredQuestionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Transactional
@Service
public class AnsweredQuestionService {
    @Resource
    private AnsweredQuestionMapper answeredQuestionMapper;

    public List<AnsweredQuestion> selectWrongQuestions(QueryInfo queryInfo){
        return answeredQuestionMapper.selectWrongQuestions(queryInfo);
    }
//    public int insertAnsweredQuestions(int sid, List<List>answerList,List<LinkedHashMap>question){
    public int insertAnsweredQuestions(int sid, List<List>answerList,List<Object>idList){
        AnsweredQuestion temp;
        for(int i=0;i<idList.size();i++){
            temp=new AnsweredQuestion();
            temp.setQid(Integer.parseInt(idList.get(i).toString()));
            temp.setSid(sid);
            if(answerList.get(i)!=null&&answerList.get(i).size()>0){
                temp.setStudentAnswer(answerList.get(i).get(0).toString());
            }
            else {
                temp.setStudentAnswer("FF");
            }
            System.out.println(temp);
            answeredQuestionMapper.addAnsweredQuestion(temp);
        }
        return 0;
    }

    public List<String> getKinds(int id){
        return answeredQuestionMapper.getKinds(id);
    }

    public int deleteBySidAndQid(int sid, int qid){ return answeredQuestionMapper.deleteBySidAndQid(sid, qid); }

    public List<HashMap> getStudentQuestionInfo(int sid){
        return answeredQuestionMapper.getStudentQuestionInfo(sid);
    }

    public List<HashMap> getQuestionAnalyseInfo(int qid){
        return answeredQuestionMapper.getQuestionAnalyseInfo(qid);
    }
}
