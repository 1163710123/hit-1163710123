package com.experiment.pojo;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "message")
public class message {
    private int m_id;
    private int m_senderid;
    private String m_content;
    private int m_reciecerid;
    private String m_data;
    private int m_isprivate;

    public message(int m_id, int m_senderid, String m_content, int m_reciecerid, String m_data, int m_isprivate) {
        this.m_id = m_id;
        this.m_senderid = m_senderid;
        this.m_content = m_content;
        this.m_reciecerid = m_reciecerid;
        this.m_data = m_data;
        this.m_isprivate = m_isprivate;
    }

    public int getM_isprivate() {
        return m_isprivate;
    }

    public void setM_isprivate(int m_isprivate) {
        this.m_isprivate = m_isprivate;
    }

    public message() {
    }

    public message(int m_id, int m_senderid, String m_content, int m_reciecerid, String m_data) {
        this.m_id = m_id;
        this.m_senderid = m_senderid;
        this.m_content = m_content;
        this.m_reciecerid = m_reciecerid;
        this.m_data = m_data;
    }

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    public int getM_senderid() {
        return m_senderid;
    }

    public void setM_senderid(int m_senderid) {
        this.m_senderid = m_senderid;
    }

    public String getM_content() {
        return m_content;
    }

    public void setM_content(String m_content) {
        this.m_content = m_content;
    }

    public int getM_reciecerid() {
        return m_reciecerid;
    }

    public void setM_reciecerid(int m_reciecerid) {
        this.m_reciecerid = m_reciecerid;
    }

    public String getM_data() {
        return m_data;
    }

    public void setM_data(String m_data) {
        this.m_data = m_data;
    }


    @Override
    public String toString() {
        return "message{" +
                "信息id=" + m_id +
                ", 信息发送者id=" + m_senderid +
                ", 信息内容='" + m_content + '\'' +
                ", 信息接收者=" + m_reciecerid +
                ", 信息日期='" + m_data + '\'' +
                '}';
    }
}
