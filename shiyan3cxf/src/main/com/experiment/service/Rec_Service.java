package com.experiment.service;

import com.experiment.pojo.hourse;
import com.experiment.pojo.house;
import com.experiment.pojo.user;
import com.experiment.pojo.users;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Administrator on 2019/6/15/015.
 */
public interface Rec_Service
{

    @GET
    @Path("/rec_login")
    @Produces("application/json")
    @Consumes("application/json")
    public user login(@QueryParam("username") String username, @QueryParam("password")String  password);

    @GET
    @Path("/gethouses")
    @Consumes("application/json")
    @Produces("application/json")
    public List<house> getHouses();

    @GET
    @Path("/getrechouses")
    @Consumes("application/json")
    @Produces("application/json")
    public List<house> getRecHouse(@QueryParam("u_id")int u_id);

    @GET
    @Path("/updatescan")
    @Consumes("application/json")
    @Produces("application/json")
    public void updateScan(@QueryParam("u_id")int u_id,@QueryParam("h_id")int h_id,@QueryParam("score") int score);

    @GET
    @Path("/getsimilarityhouses")
    @Consumes("application/json")
    @Produces("application/json")
    public List<house> getSimHouse(@QueryParam("u_id")int u_id);

    @GET
    @Path("/getsimuser")
    @Consumes("application/json")
    @Produces("application/json")
    public int getSimUserHouse(@QueryParam("u_id")int u_id);

    @GET
    @Path("/getpredict")
    @Produces("text/xml")
    public String getPredict();
}
