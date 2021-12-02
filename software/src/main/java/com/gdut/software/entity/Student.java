package com.gdut.software.entity;

import java.util.List;

public class Student {
    private int id;
    private String name;
    private int age;
    private List<AnsweredQuestion> AnsweredQuestion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<AnsweredQuestion> getAnsweredQuestions() {
        return AnsweredQuestion;
    }

    public void setAnsweredQuestions(List<AnsweredQuestion> AnsweredQuestion) {
        this.AnsweredQuestion = AnsweredQuestion;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", wrongQuestions=" + AnsweredQuestion +
                '}';
    }
}
