package com.experiment.service;

import com.experiment.pojo.appraise;
import com.experiment.pojo.hourse;
import com.experiment.pojo.message;
import com.experiment.pojo.users;
import org.apache.logging.log4j.core.config.json.JsonConfiguration;
import org.springframework.web.bind.annotation.RequestParam;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;


@WebService
//@Path("/service")
public interface Service {
    @GET
    @Path("/getuserbyid")
    @Consumes("application/json")
    @Produces("application/json")
    public users getuserbyid(@QueryParam("id") Integer id);


    @GET
    @Path("/checkusername")
    @Consumes("application/json")
    @Produces("application/json")
    public users checkusername(@QueryParam("username") String username);


    @POST
    @Path("/insertUuser")
    @Consumes("application/json")
    public boolean insertUser(@QueryParam("username") String usernmae,@QueryParam("password") String password, @QueryParam("telephone") String telephone, @QueryParam("sex") String sex);

    @GET
    @Path("/login")
    @Produces("application/json")
    @Consumes("application/json")
    public users login(@QueryParam("username") String username, @QueryParam("password")String  password);

    @GET
    @Path("/getallusers")
    @Produces("application/json")
    public List<users> getUsers();


    @POST
    @Path("/updateuser")
    @Consumes("application/json")
    public boolean updateUser(@QueryParam("id") Integer id, @QueryParam("username") String username, @QueryParam("password") String password, @QueryParam("telephone") String telephone, @QueryParam("sex") String sex);

    @GET
    @Path("/getallHourse")
    @Produces("application/json")
    public List<hourse> getallHourse();

    @GET
    @Path("/choosehourse/{id}")
    @Produces("application/json")
    @Consumes("application/json")
    public List<hourse> choosehourse(@PathParam("id") int id);


    @DELETE
    @Path("/deleteHourse")
    @Consumes("application/json")
    public void deletehourse(@QueryParam("id") int id);

    @POST
    @Path("/inisertHourse")
    @Consumes("application/json")
    public void insertHourse(@QueryParam("name") String name,@QueryParam("price")double price , @QueryParam("address") String address,@QueryParam("produce") String produce,@QueryParam("user") int user);

    @PUT
    @Path("/updateHourse")
    @Consumes("application/json")
    public void updateHourse(@QueryParam("id") int id,@QueryParam("name") String name,@QueryParam("price")double price , @QueryParam("address") String address,@QueryParam("produce") String produce,@QueryParam("user") int user);

    @GET
    @Path("/getuserhourse/{id}")
    @Produces("application/json")
    @Consumes("application/json")
    public List<hourse> getuserhourse(@PathParam("id") Integer id);

    @GET
    @Path("/getulendhourse/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public List<hourse> getlendhourse(@PathParam("id") Integer id);


    @PUT
    @Path("/lendhourse")
    @Consumes("application/json")
    public void lendhourse(@QueryParam("id") int lenderid,@QueryParam("hourseid") int hourseid);


    @GET
    @Path("/getrecommendationhouse/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public List<hourse> getRecommendationHouse(@PathParam("id")int id);

    @GET
    @Path("/getSurroundHouse/{lng}/{lat}")
    @Consumes("application/json")
    @Produces("application/json")
    public List<hourse> getSurroundHouse(@PathParam("lng")double lng, @PathParam("lat") double lat);

//    @GET
//    @Path("/getDistance/{a1}/{a2}")
//    @Consumes("application/json")
//    @Produces("application/json")
//    public List<hourse> getDistance(@PathParam("a1")String a1, @PathParam("a2") String a2);

}