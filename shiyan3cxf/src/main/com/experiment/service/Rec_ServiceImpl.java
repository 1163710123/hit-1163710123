package com.experiment.service;

import com.experiment.mapper.HouseMapper;
import com.experiment.mapper.ScanMapper;
import com.experiment.mapper.UserMapper;
import com.experiment.pojo.house;
import com.experiment.pojo.scan;
import com.experiment.pojo.user;
import com.experiment.tool.Recommend;
import com.experiment.utils.Sql;
import org.apache.ibatis.session.SqlSession;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/6/15/015.
 */
public class Rec_ServiceImpl implements Rec_Service
{
    private static SqlSession s = Sql.getSql();
    private static int Row = 10;

    @Override
    public user login(String username, String password)
    {
        user u= s.getMapper(UserMapper.class).login(username,password);
        System.out.println("登录服务被调用");
//        System.out.println(u.toString());
        return u;
    }

    @Override
    public List<house> getHouses()
    {
        List<house> list = s.getMapper(HouseMapper.class).getallhouses();
        return list;
    }

    @Override
    public List<house> getRecHouse(int u_id)
    {
        List<scan> scan = s.getMapper(ScanMapper.class).getscan(u_id);
        if(scan.size()!= 0)
        {
            Recommend recommend = new Recommend();
            float[][] a = recommend.similarity();
            int[] out = recommend.getRecommend(scan, a);
            List<house> houses = s.getMapper(HouseMapper.class).getallhouses();
            List<house> sort = new ArrayList<>();
            for (int i = 0; i < out.length; i++)
            {
                sort.add(houses.get(out[i]));
//                System.out.println(houses.get(out[i]).getH_id());
            }
//        List<house> houses = s.getMapper(HouseMapper.class).getallhouses();
            return sort;
        }else{
            int sim  = getSimUserHouse(u_id);
            scan = s.getMapper(ScanMapper.class).getscan(sim);
            Recommend recommend = new Recommend();
            float[][] a = recommend.similarity();
            int[] out = recommend.getRecommend(scan, a);
            List<house> houses = s.getMapper(HouseMapper.class).getallhouses();
            List<house> sort = new ArrayList<>();
            for (int i = 0; i < out.length; i++)
            {
                sort.add(houses.get(out[i]));
//                System.out.println(houses.get(out[i]).getH_id());
            }
//        List<house> houses = s.getMapper(HouseMapper.class).getallhouses();
            return sort;
        }
    }

    @Override
    public void updateScan(int u_id, int h_id, int score)
    {
        scan sc = s.getMapper(ScanMapper.class).getscanbyid(u_id,h_id);
        if(sc == null){
            sc = new scan(u_id,h_id,score);
            System.out.println("is null");
            s.getMapper(ScanMapper.class).insertscan(sc);
        }else{
            if(sc.getScore() < score){
                sc = new scan(u_id,h_id,score);
                System.out.println("update");
                s.getMapper(ScanMapper.class).updatescan(sc);
            }
        }
    }

    @Override
    public List<house> getSimHouse(int u_id)
    {
        Recommend recommend = new Recommend();
        float[][] sim = recommend.similarity();
        float[] house = new float[Row];
        for (int i = 0;i < Row;i++){
            house[i] = sim[u_id - 1][i];
        }
        int[] out = recommend.Arraysort(house);
        List<house> houses = s.getMapper(HouseMapper.class).getallhouses();
        List<house> sort = new ArrayList<>();
        for (int i = 0; i < out.length; i++)
        {
            sort.add(houses.get(out[i]));
        }
        return sort;
    }

    @Override
    public int getSimUserHouse(int u_id)
    {
        Recommend recommend = new Recommend();
        List<user> users = s.getMapper(UserMapper.class).getusers();
        int sim_user = recommend.getUserSimilarity(u_id, users);
        return sim_user;
    }

    @Override
    public String getPredict()
    {
        File file = new File("D:\\Javacx\\shiyan3cxf\\src\\main\\com\\experiment\\data\\output.txt");
        BufferedReader reader = null;
        StringBuilder stringBuilder = new StringBuilder();
        try
        {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                stringBuilder.append(tempString + "\n");
            }
//            System.out.println(stringBuilder.toString());
            return stringBuilder.toString();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return "null";
    }


}
