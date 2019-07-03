package com.experiment.pojo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Administrator on 2019/6/14/014.
 */
@XmlRootElement(name = "scan")
public class scan
{
    private int u_id;
    private int h_id;
    private int score;

    public scan(int u_id, int h_id, int score)
    {
        this.u_id = u_id;
        this.h_id = h_id;
        this.score = score;
    }

    public int getU_id()
    {
        return u_id;
    }

    public void setU_id(int u_id)
    {
        this.u_id = u_id;
    }

    public int getH_id()
    {
        return h_id;
    }

    public void setH_id(int h_id)
    {
        this.h_id = h_id;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    @Override
    public String toString()
    {
        return "scan{" +
                "u_id=" + u_id +
                ", h_id=" + h_id +
                ", score=" + score +
                '}';
    }
}
