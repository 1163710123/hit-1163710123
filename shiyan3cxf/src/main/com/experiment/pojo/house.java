package com.experiment.pojo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Administrator on 2019/6/15/015.
 */
@XmlRootElement(name = "house")
public class house
{
    private int h_id;
    private String h_name;
    private int wei;
    private int ting;
    private int shi;
    private float area;
    private String direction;
    private String floor;
    private float price;
    private String location;

    public int getH_id()
    {
        return h_id;
    }

    public void setH_id(int h_id)
    {
        this.h_id = h_id;
    }

    public String getH_name()
    {
        return h_name;
    }

    public void setH_name(String h_name)
    {
        this.h_name = h_name;
    }

    public int getWei()
    {
        return wei;
    }

    public void setWei(int wei)
    {
        this.wei = wei;
    }

    public int getTing()
    {
        return ting;
    }

    public void setTing(int ting)
    {
        this.ting = ting;
    }

    public int getShi()
    {
        return shi;
    }

    public void setShi(int shi)
    {
        this.shi = shi;
    }

    public float getArea()
    {
        return area;
    }

    public void setArea(float area)
    {
        this.area = area;
    }

    public String getDirection()
    {
        return direction;
    }

    public void setDirection(String direction)
    {
        this.direction = direction;
    }

    public String getFloor()
    {
        return floor;
    }

    public void setFloor(String floor)
    {
        this.floor = floor;
    }

    public float getPrice()
    {
        return price;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    @Override
    public String toString()
    {
        return "house{" +
                "h_id=" + h_id +
                ", h_name='" + h_name + '\'' +
                ", wei=" + wei +
                ", ting=" + ting +
                ", shi=" + shi +
                ", area=" + area +
                ", direction='" + direction + '\'' +
                ", floor='" + floor + '\'' +
                ", price=" + price +
                ", location='" + location + '\'' +
                '}';
    }
}
