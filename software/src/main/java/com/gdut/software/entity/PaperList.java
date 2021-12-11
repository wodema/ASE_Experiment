package com.gdut.software.entity;

import java.sql.Timestamp;

/**
 * @author 22427(king0liam)
 * @version 1.0
 * @ClassName: PaperList
 * @Description
 * @Date: 2021/12/3 13:50
 * @since version-0.0
 */
public class PaperList {
    private int paper_id;
    private String paper_name;
    private java.sql.Timestamp paper_date;
    private String total_time;
    private int total_score;

    public int getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(int paper_id) {
        this.paper_id = paper_id;
    }

    public String getPaper_name() {
        return paper_name;
    }

    public void setPaper_name(String paper_name) {
        this.paper_name = paper_name;
    }

    public Timestamp getPaper_date() {
        return paper_date;
    }

    public void setPaper_date(Timestamp paper_date) {
        this.paper_date = paper_date;
    }

    public String getTotal_time() {
        return total_time;
    }

    public void setTotal_time(String total_time) {
        this.total_time = total_time;
    }

    public int getTotal_score() {
        return total_score;
    }

    public void setTotal_score(int total_score) {
        this.total_score = total_score;
    }

}
