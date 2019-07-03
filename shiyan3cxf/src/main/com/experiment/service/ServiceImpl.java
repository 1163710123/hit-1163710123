package com.experiment.service;

import com.experiment.Example;
import com.experiment.mapper.HourseMapper;
import com.experiment.mapper.UsersMapper;
import com.experiment.pojo.hourse;
import com.experiment.pojo.users;
import com.experiment.tool.FindSurroundHouse;
import com.experiment.tool.Similarity;
import com.experiment.utils.Sql;
import org.apache.ibatis.session.SqlSession;
import org.codehaus.jettison.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ServiceImpl implements Service {
    private static SqlSession s = Sql.getSql();
    private static final double EARTH_RADIUS = 6371393;

    @Override
    public users login(String username, String password) {
        List<users> list = s.getMapper(UsersMapper.class).checkUser(username,password);
        System.out.println("登录服务被调用");
        if(list.size() == 0){
            return null;
        }
        return list.get(0);
    }

    @Override
    public users getuserbyid(Integer id) {
        System.out.println("用户id服务被调用了");
        List<users> list = s.getMapper(UsersMapper.class).getuserbyid(id);
        if(list.size() ==0){
            return null;
        }
        return list.get(0);
    }

    @Override
    public users checkusername(String username) {
        System.out.println(username);
        List<users> list = s.getMapper(UsersMapper.class).getusernmae(username);
        System.out.println(list);
        if(list.size() == 0){
            System.out.println("用户名字不存在");
            return null;
        }else{
            System.out.println("用户名字存在");
            return list.get(0);
        }
    }

    @Override
    public List<users> getUsers() {
        List<users> list = s.getMapper(UsersMapper.class).getallUser();
        s.commit();
        return list;
    }

    @Override
    public List<hourse> getuserhourse(Integer id) {
        return s.getMapper(HourseMapper.class).getuserhourse(id);
    }


    @Override
    public List<hourse> getlendhourse(Integer id) {
       List<hourse> list =  s.getMapper(HourseMapper.class).getlendhourse(id);
       if(list.size() == 0){
           return null;
       }
       return list;
    }

    @Override
    public boolean insertUser(String usernmae, String password, String telephone, String sex) {
        System.out.println(usernmae);
        try{
            s.getMapper(UsersMapper.class).insertUser(new users(0,usernmae,password,telephone,sex,new SimpleDateFormat().format(new Date())));
            s.commit();
            System.out.println("注册成功");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("注册失败");
            return false;
        }
    }

    @Override
    public boolean updateUser(Integer id, String username, String password, String telephone, String sex) {
        JSONObject jsonObject = new JSONObject();
        System.out.println("asdfasdfasdfasdfasdf"+sex);
        s.getMapper(UsersMapper.class).updateUser(new users(id,username,password,telephone,sex,new SimpleDateFormat().format(new Date())));
        s.commit();
        System.out.println("更新数据成功");
        return true;
    }


    @Override
    public List<hourse> getallHourse(){
        List<hourse> list = Sql.getSql().getMapper(HourseMapper.class).getallhourses();
//        List<hourse> list1 = new ArrayList<>();
//        System.out.println("获取房源信息");
//        System.out.println(list);
//        for (hourse h : list){
//            if(h.getH_lenderid() == h.getH_userid()){
//                list1.add(h);
//            }
//        }
//        System.out.println(list1);
        return list;

    }

    @Override
    public List<hourse> choosehourse(int id)
    {
        return s.getMapper(HourseMapper.class).choosehourse(id);
    }

    @Override
    public void deletehourse(int id) {
        s.getMapper(HourseMapper.class).deleteHourse(id);
        s.commit();
    }



    @Override
    public void insertHourse(String name, double price, String address, String produce, int user) {
        System.out.println(produce);
        s.getMapper(HourseMapper.class).insertHourse(new hourse(0,name,address, Example.fenci(produce),price,user,user,(new SimpleDateFormat().format(new Date())).toString()));
        s.commit();
    }

    @Override
    public void updateHourse(int id ,String name, double price, String address, String produce, int user) {
        System.out.println(produce);
        s.getMapper(HourseMapper.class).updatehourse(new hourse(id,name,address,produce,price,user,user,(new SimpleDateFormat().format(new Date())).toString()));
        s.commit();
    }



    @Override
    public void lendhourse(int lenderid, int hourseid) {
        List<hourse> list = s.getMapper(HourseMapper.class).choosehourse(hourseid);
        hourse h = list.get(0);
        h.setH_lenderid(lenderid);
        s.getMapper(HourseMapper.class).updatehourse(h);
        s.commit();
    }

    @Override
    public List<hourse> getRecommendationHouse(int id){
        List<hourse> list = s.getMapper(HourseMapper.class).choosehourse(id);
        hourse house = list.get(0);
        List<hourse> allhouse = s.getMapper(HourseMapper.class).getallhourses();
        Similarity similarity = new Similarity();
        Map<Integer,Double> result = similarity.getSimilarHouse(allhouse,house);
        List<hourse> outcome = new ArrayList<>();
        for(int i : result.keySet()){
            if(result.get(i) != 0){
                outcome.add(choosehourse(i).get(0));
            }
        }
        return outcome;
    }

    @Override
    public List<hourse> getSurroundHouse(double lng,double lat){
        List<hourse> list = s.getMapper(HourseMapper.class).getallhourses();
//        System.out.println(list.toString());
        FindSurroundHouse findSurroundHouse = new FindSurroundHouse();
        List<hourse> distance = findSurroundHouse.getNearDistance(lng,lat,list);
        System.out.println("result = " + distance.toString());
        return distance;
    }


//    @Override
//    public List<hourse> getDistance(String a1, String a2)
//    {
//        Location
//        return ;
//    }


}