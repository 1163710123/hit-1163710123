package com.experiment.mapper;

import com.experiment.pojo.user;

import java.util.List;

public interface UserMapper
{
    /**
     * 登陆是检测用户
     * @param username
     * @param password
     * @return 用户
     */
    public user login(String username, String password);

//    /**
//     * 注册用户
//     * @param users
//     *
//     */
//    public void insertUser(users users);
//
//
//    /**
//     * 更新用户
//     * @param users
//     */
//    public void updateUser(users users);
//
    public List<user> getusers();
//
//    public List<users> getusernmae(String username);
//
//    public List<users> getuserbyid(Integer id);

}
