package com.experiment.server1;

import com.experiment.Example;
import com.experiment.mapper.HourseMapper;
import com.experiment.mapper.UsersMapper;
import com.experiment.utils.Sql;
import com.experiment.pojo.hourse;
import com.experiment.pojo.users;
import org.apache.ibatis.session.SqlSession;

import javax.jws.WebService;
import javax.ws.rs.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@WebService
//@Path("/services")
public class ServiceImpl implements Service {
    private static SqlSession s = Sql.getSql();

    @GET
    @Path("/login")
    @Produces("application/json")
    @Consumes("application/json")
    public users login(@QueryParam("username") String username, @QueryParam("password")String  password){
        List<users> list = s.getMapper(UsersMapper.class).checkUser(username,password);
        System.out.println("登录服务被调用");
        if(list.size() == 0){
            return null;
        }
        return list.get(0);
    }

    @GET
    @Path("/getuserbyid")
    @Consumes("application/json")
    @Produces("application/json")
    public users getuserbyid(@QueryParam("id") Integer id){
        System.out.println("用户id服务被调用了");
        List<users> list = s.getMapper(UsersMapper.class).getuserbyid(id);
        if(list.size() ==0){
            return null;
        }
        return list.get(0);
    }

    @GET
    @Path("/checkusername")
    @Consumes("application/json")
    @Produces("application/json")
    public users checkusername(@QueryParam("username") String username){
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

    @POST
    @Path("/insertUuser")
    @Consumes("application/json")
    public boolean insertUser(@QueryParam("username") String usernmae, @QueryParam("password") String password, @QueryParam("telephone") String telephone, @QueryParam("sex") String sex) {
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

    @GET
    @Path("/getallusers")
    @Produces("application/json")
    public List<users> getUsers() {
        List<users> list = s.getMapper(UsersMapper.class).getallUser();
        s.commit();
        return list;
    }


    @GET
    @Path("/getuserhourse/{id}")
    @Produces("application/json")
    @Consumes("application/json")
    public List<hourse> getuserhourse(@PathParam("id") Integer id) {
        return s.getMapper(HourseMapper.class).getuserhourse(id);
    }


    @GET
    @Path("/getulendhourse/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public List<hourse> getlendhourse(@PathParam("id") Integer id) {
       List<hourse> list =  s.getMapper(HourseMapper.class).getlendhourse(id);
       if(list.size() == 0){
           return null;
       }
       return list;
    }



    @POST
    @Path("/updateuser")
    @Consumes("application/json")
    public boolean updateUser(@QueryParam("id") Integer id, @QueryParam("username") String username, @QueryParam("password") String password, @QueryParam("telephone") String telephone, @QueryParam("sex") String sex){
        System.out.println("asdfasdfasdfasdfasdf"+sex);
        s.getMapper(UsersMapper.class).updateUser(new users(id,username,password,telephone,sex,new SimpleDateFormat().format(new Date())));
        s.commit();
        System.out.println("更新数据成功");
        return true;
    }

    @GET
    @Path("/getallHourse")
    @Produces("application/json")
    public List<hourse> getallHourse(){
        List<hourse> list = Sql.getSql().getMapper(HourseMapper.class).getallhourses();
        List<hourse> list1 = new ArrayList<>();
        System.out.println("获取房源信息");
        System.out.println(list);
        for (hourse h : list){
            if(h.getH_lenderid() == h.getH_userid()){
                list1.add(h);
            }
        }
        return list1;
    }

    @DELETE
    @Path("/deleteHourse")
    @Consumes("application/json")
    public void deletehourse(@QueryParam("id") int id){
        s.getMapper(HourseMapper.class).deleteHourse(id);
        s.commit();
    }


    @POST
    @Path("/inisertHourse")
    @Consumes("application/json")
    public void insertHourse(@QueryParam("name") String name, @QueryParam("price")double price , @QueryParam("address") String address, @QueryParam("produce") String produce, @QueryParam("user") int user){
        System.out.println(produce);
        s.getMapper(HourseMapper.class).insertHourse(new hourse(0,name,address, Example.fenci(produce),price,user,user,(new SimpleDateFormat().format(new Date())).toString()));
        s.commit();
    }

    @PUT
    @Path("/updateHourse")
    @Consumes("application/json")
    public void updateHourse(@QueryParam("id") int id, @QueryParam("name") String name, @QueryParam("price")double price , @QueryParam("address") String address, @QueryParam("produce") String produce, @QueryParam("user") int user){
        System.out.println(produce);
        s.getMapper(HourseMapper.class).updatehourse(new hourse(id,name,address,produce,price,user,user,(new SimpleDateFormat().format(new Date())).toString()));
        s.commit();
    }



    @PUT
    @Path("/lendhourse")
    @Consumes("application/json")
    public void lendhourse(@QueryParam("id") int lenderid, @QueryParam("hourseid") int hourseid){
        List<hourse> list = s.getMapper(HourseMapper.class).choosehourse(hourseid);
        hourse h = list.get(0);
        h.setH_lenderid(lenderid);
        s.getMapper(HourseMapper.class).updatehourse(h);
        s.commit();
    }



}