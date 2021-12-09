package com.gdut.software.controller;

import com.alibaba.fastjson.JSON;
import com.gdut.software.entity.QueryInfo;
import com.gdut.software.entity.Student;
import com.gdut.software.service.PaperListService;
import com.gdut.software.service.StudentService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Resource
    private StudentService studentService;
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody Map<String, Object> para){
        Student temp = studentService.login(Integer.parseInt(para.get("sid").toString()),
                para.get("password").toString());
        HashMap<String, Object> map = new HashMap<>();
        map.put("student", temp);
        return JSON.toJSONString(map);
    }
    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public String addStudent(@RequestBody Map<String, Object> para){
        Student tempStudent = new Student();
        tempStudent.setName("XXX");
        tempStudent.setAge(18);
        int affectedRecordNumber = studentService.addStudent(tempStudent);
        HashMap<String, Object> map = new HashMap<>();
        map.put("affectedRecordNumber", affectedRecordNumber);
        return JSON.toJSONString(map);
    }
    @RequestMapping(value = "/deleteStudent", method = RequestMethod.POST)
    public String deleteStudent(@RequestBody Map<String, Object> para){
        int affectedRecordNumber = studentService.deleteStudent(Integer.parseInt(para.get("id").toString()));
        HashMap<String, Object> map = new HashMap<>();
        map.put("int affectedRecordNumber",affectedRecordNumber);
        return JSON.toJSONString(map);
    }
    @RequestMapping(value = "/updateStudentList", method = RequestMethod.POST)
    public String updateStudentList(@RequestBody Map<String, Object> para){
        int affectedRecordNumber= studentService.updatePaperList((List<Map<String, Object>>)para.get("tableData4sort"));
        HashMap<String, Object> map = new HashMap<>();
        map.put("int affectedRecordNumber",affectedRecordNumber);
        return JSON.toJSONString(map);
    }
    @RequestMapping(value = "/getStudentList", method = RequestMethod.POST)
    public String getStudentList8LJL(@RequestBody Map<String, Object> para){
        List<Student> sList= studentService.getStudentList();
        int count = sList.size();
        HashMap<String, Object> map = new HashMap<>();
        map.put("studentList", sList);
        map.put("number", count);
        return JSON.toJSONString(map);
    }
    @RequestMapping(value = "/getStudents", method = RequestMethod.POST)
    public String getStudentList(@RequestBody QueryInfo queryInfo){
        queryInfo.setPage((queryInfo.getPage() - 1) * queryInfo.getSize());
        List<Student> sList = studentService.getStudents(queryInfo);
        int count = sList.size();
        HashMap<String, Object> map = new HashMap<>();
        map.put("studentList", sList);
        map.put("number", count);
        return JSON.toJSONString(map);
    }
}
