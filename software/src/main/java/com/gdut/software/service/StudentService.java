package com.gdut.software.service;

import com.gdut.software.entity.QueryInfo;
import com.gdut.software.entity.Student;
import com.gdut.software.mapper.StudentMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentService {
    @Resource
    private StudentMapper studentMapper;

    public List<Student> getStudents(QueryInfo queryInfo){
        return studentMapper.getStudents(queryInfo);
    }

    public Student login(int id, String password){
        return studentMapper.login(id,password);
    }

}
