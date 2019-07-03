package com.experiment.pojo;
        import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "users")
public class users {
    private int u_id;
    private String u_username;
    private String u_password;
    private String u_telephone;
    private String u_sex;
    private String u_data;
//    private String u_birth;

    public users(int u_id, String u_username, String u_password, String u_telephone, String u_sex, String u_data) {
        this.u_id = u_id;
        this.u_username = u_username;
        this.u_password = u_password;
        this.u_telephone = u_telephone;
        this.u_sex = u_sex;
        this.u_data = u_data;
    }

    public String getU_sex() {
        return u_sex;
    }

    public void setU_sex(String u_sex) {
        this.u_sex = u_sex;
    }

    public String getU_data() {
        return u_data;
    }

    public void setU_data(String u_data) {
        this.u_data = u_data;
    }



    public users() {
    }


    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getU_username() {
        return u_username;
    }

    public void setU_username(String u_username) {
        this.u_username = u_username;
    }

    public String getU_password() {
        return u_password;
    }

    public void setU_password(String u_password) {
        this.u_password = u_password;
    }


    public String getU_telephone() {
        return u_telephone;
    }

    public void setU_telephone(String u_telephone) {
        this.u_telephone = u_telephone;
    }

    @Override
    public String toString() {
        return "users{" +
                "u_id=" + u_id +
                ", u_username='" + u_username + '\'' +
                ", u_password='" + u_password + '\'' +
                ", u_telephone='" + u_telephone + '\'' +
                ", u_sex='" + u_sex + '\'' +
                ", u_data='" + u_data + '\'' +
                '}';
    }
}