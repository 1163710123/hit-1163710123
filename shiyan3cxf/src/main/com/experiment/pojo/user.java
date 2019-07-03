package com.experiment.pojo;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by Administrator on 2019/6/15/015.
 */
@XmlRootElement(name = "user")
public class user
{
    private int u_id;
    private String u_name;
    private String u_password;
    private String u_telephone;
    private String u_sex;
    private Date u_birth;
    private String u_address;

    public int getU_id()
    {
        return u_id;
    }

    public void setU_id(int u_id)
    {
        this.u_id = u_id;
    }

    public String getU_name()
    {
        return u_name;
    }

    public void setU_name(String u_name)
    {
        this.u_name = u_name;
    }

    public String getU_password()
    {
        return u_password;
    }

    public void setU_password(String u_password)
    {
        this.u_password = u_password;
    }

    public String getU_telephone()
    {
        return u_telephone;
    }

    public void setU_telephone(String u_telephone)
    {
        this.u_telephone = u_telephone;
    }

    public String getU_sex()
    {
        return u_sex;
    }

    public void setU_sex(String u_sex)
    {
        this.u_sex = u_sex;
    }

    public Date getU_birth()
    {
        return u_birth;
    }

    public void setU_birth(Date u_birth)
    {
        this.u_birth = u_birth;
    }

    public String getU_address()
    {
        return u_address;
    }

    public void setU_address(String u_address)
    {
        this.u_address = u_address;
    }

    @Override
    public String toString()
    {
        return "user{" +
                "u_id=" + u_id +
                ", u_name='" + u_name + '\'' +
                ", u_password='" + u_password + '\'' +
                ", u_telephone='" + u_telephone + '\'' +
                ", u_sex='" + u_sex + '\'' +
                ", u_birth=" + u_birth +
                ", u_address='" + u_address + '\'' +
                '}';
    }
}
