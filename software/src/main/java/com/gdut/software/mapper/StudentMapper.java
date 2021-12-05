package com.gdut.software.mapper;

import com.gdut.software.entity.QueryInfo;
import com.gdut.software.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    public Student selectStudentById(int id);

    public int addStudent(Student student);

    public List<Student> getStudents(QueryInfo queryInfo);
}
