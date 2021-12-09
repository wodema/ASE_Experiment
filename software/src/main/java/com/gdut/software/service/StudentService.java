package com.gdut.software.service;

import com.gdut.software.entity.QueryInfo;
import com.gdut.software.entity.Student;
import com.gdut.software.mapper.PaperListMapper;
import com.gdut.software.mapper.StudentMapper;
//import com.gdut.software.mapper.PaperMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private PaperListMapper paperListMapper;
    public int addStudent(Student student){
        return studentMapper.addStudent(student);
    }
    public List<Student> getStudents(QueryInfo queryInfo){
        return studentMapper.getStudents(queryInfo);
    }
    public List<Student> getStudentList(){
        return studentMapper.getStudentList();
    }
    public int updatePaperList(List<Map<String, Object>> identityList){
        return paperListMapper.updatePaperList("student", identityList,identityList.get(0));
    }
    public int deleteStudent(Integer id){
        return studentMapper.deleteStudent(id);
    }
    public Student login(int id, String password){
        return studentMapper.login(id,password);
    }

}
