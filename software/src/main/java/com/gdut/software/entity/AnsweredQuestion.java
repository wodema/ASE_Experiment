package com.gdut.software.entity;

public class AnsweredQuestion {
    private int id;
    private int qid;
    private int sid;
    private int count;
    private String studentAnswer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    @Override
    public String toString() {
        return "AnsweredQuestion{" +
                "id=" + id +
                ", qid=" + qid +
                ", sid=" + sid +
                ", count=" + count +
                ", studentAnswer='" + studentAnswer + '\'' +
                '}';
    }
}
