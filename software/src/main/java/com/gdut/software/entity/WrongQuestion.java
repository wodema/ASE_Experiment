package com.gdut.software.entity;

public class WrongQuestion {
    private int id;
    private int sid;
    private int qid;
    private int number;
    private String question;
    private String kind;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @Override
    public String toString() {
        return "WrongQuestion{" +
                "id=" + id +
                ", count=" + number +
                ", question='" + question + '\'' +
                ", kind='" + kind + '\'' +
                '}';
    }
}
