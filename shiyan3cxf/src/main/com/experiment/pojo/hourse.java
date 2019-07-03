package com.experiment.pojo;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "hourse")
public class hourse {
    private int h_id;   // 序号
    private String h_name;
    private String h_address;   // 地址
    private String h_produce;   // 简介
    private double h_price;     // 价格
    private int h_userid;      //  出租者
    private int h_lenderid;
    private String h_data;
    public hourse(){

    }
    public hourse(int h_id, String h_name, String h_address, String h_produce, double h_price, int h_userid, int h_lenderid, String h_data) {
        this.h_id = h_id;
        this.h_name = h_name;
        this.h_address = h_address;
        this.h_produce = h_produce;
        this.h_price = h_price;
        this.h_userid = h_userid;
        this.h_lenderid = h_lenderid;
        this.h_data = h_data;
    }

    public String getH_name() {
        return h_name;
    }

    public void setH_name(String h_name) {
        this.h_name = h_name;
    }

    public int getH_lenderid() {
        return h_lenderid;
    }

    public void setH_lenderid(int h_lenderid) {
        this.h_lenderid = h_lenderid;
    }

    public String getH_data() {
        return h_data;
    }

    public void setH_data(String h_data) {
        this.h_data = h_data;
    }

    public int getH_id() {
        return h_id;
    }

    public void setH_id(int h_id) {
        this.h_id = h_id;
    }

    public String getH_address() {
        return h_address;
    }

    public void setH_address(String h_address) {
        this.h_address = h_address;
    }

    public String getH_produce() {
        return h_produce;
    }

    public void setH_produce(String h_produce) {
        this.h_produce = h_produce;
    }

    public double getH_price() {
        return h_price;
    }

    public void setH_price(double h_price) {
        this.h_price = h_price;
    }

    public int getH_userid() {
        return h_userid;
    }

    public void setH_userid(int h_userid) {
        this.h_userid = h_userid;
    }

    @Override
    public String toString() {
        return "hourse{" +
                "h_id=" + h_id +
                ", h_name='" + h_name + '\'' +
                ", h_address='" + h_address + '\'' +
                ", h_produce='" + h_produce + '\'' +
                ", h_price=" + h_price +
                ", h_userid=" + h_userid +
                ", h_lenderid=" + h_lenderid +
                ", h_data='" + h_data + '\'' +
                '}';
    }
}
