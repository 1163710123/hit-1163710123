package com.experiment.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "appraise")
public class appraise {
    private int a_id;
    private String a_hourseappraise;
    private int a_userid;
    private int a_hourseid;
    private String a_data;
    public appraise() {
    }
    public appraise(int a_id, String a_hourseappraise, int a_userid, int a_hourseid, String a_data) {
        this.a_id = a_id;
        this.a_hourseappraise = a_hourseappraise;
        this.a_userid = a_userid;
        this.a_hourseid = a_hourseid;
        this.a_data = a_data;
    }

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public String getA_data() {
        return a_data;
    }

    public void setA_data(String a_data) {
        this.a_data = a_data;
    }

    public String getA_hourseappraise() {
        return a_hourseappraise;
    }

    public void setA_hourseappraise(String a_hourseappraise) {
        this.a_hourseappraise = a_hourseappraise;
    }

    public int getA_userid() {
        return a_userid;
    }

    public void setA_userid(int a_userid) {
        this.a_userid = a_userid;
    }

    public int getA_hourseid() {
        return a_hourseid;
    }

    public void setA_hourseid(int a_hourseid) {
        this.a_hourseid = a_hourseid;
    }

    @Override
    public String toString() {
        return "appraise{" +
                "a_id=" + a_id +
                ", a_hourseappraise='" + a_hourseappraise + '\'' +
                ", a_userid=" + a_userid +
                ", a_hourseid=" + a_hourseid +
                ", a_data='" + a_data + '\'' +
                '}';
    }
}
