package com.experiment.server1;

import com.experiment.pojo.hourse;
import com.experiment.pojo.users;

import java.util.List;


public interface Service {


    public users getuserbyid(Integer id);


    public users checkusername(String username);


    public boolean insertUser(String usernmae, String password, String telephone, String sex);

    public users login(String username, String password);

    public List<users> getUsers();

    public boolean updateUser(Integer id, String username, String password, String telephone, String sex);

    public List<hourse> getallHourse();

    public void deletehourse(int id);

    public void insertHourse(String name, double price, String address, String produce, int user);

    public void updateHourse(int id, String name, double price, String address, String produce, int user);


    public List<hourse> getuserhourse(Integer id);

    public List<hourse> getlendhourse(Integer id);

    public void lendhourse(int lenderid, int hourseid);


}