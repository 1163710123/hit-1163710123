package com.experiment.tool;


import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;

/**
 * Created by Administrator on 2019/3/25/025.
 */
public class Location
{
    private String AK = "aeiHIIgeMRuvbpx4aBRZZ6z2C97bq6x7"; // 百度地图密钥
    private static final double EARTH_RADIUS = 6371393; // 平均半径,单位：m
//    public static void main(String[] args) {
//        String dom = "北京王府井";
//        String coordinate = getCoordinate(dom);
//        System.out.println("'" + dom + "'的经纬度为：" + coordinate);
//        // System.err.println("######同步坐标已达到日配额6000限制，请明天再试！#####");
//    }

    public static void main(String[] args)
    {
        Location location = new Location();
        double distance = location.getDistance("哈尔滨中央大街", "哈尔滨市哈尔滨工业大学");
        System.out.println(distance);
    }
    public double getDistance(String address1,String address2){
        double address1_X =  Math.toRadians(Double.parseDouble(getCoordinate(address1).split(",")[0]));
        double address1_Y =  Math.toRadians(Double.parseDouble(getCoordinate(address1).split(",")[1]));
        double address2_X =  Math.toRadians(Double.parseDouble(getCoordinate(address2).split(",")[0]));
        double address2_Y =  Math.toRadians(Double.parseDouble(getCoordinate(address2).split(",")[1]));
        double cos = Math.cos(address1_Y) * Math.cos(address2_Y) * Math.cos(address1_X - address2_X)
                + Math.sin(address1_Y) * Math.sin(address2_Y);
        double acos = Math.acos(cos); // 反余弦值
        return EARTH_RADIUS * acos; // 最终结果
    }

    public double getDistance(double lng,double lat,String address2){
//        System.out.println(address2);
        double address1_X =  Math.toRadians(lng);
        double address1_Y =  Math.toRadians(lat);
        double address2_X =  Math.toRadians(Double.parseDouble(getCoordinate(address2).split(",")[0]));
        double address2_Y =  Math.toRadians(Double.parseDouble(getCoordinate(address2).split(",")[1]));
        double cos = Math.cos(address1_Y) * Math.cos(address2_Y) * Math.cos(address1_X - address2_X)
                + Math.sin(address1_Y) * Math.sin(address2_Y);
//        System.out.println(cos);
        double acos = Math.acos(cos); // 反余弦值
//        System.out.println(acos);
        return EARTH_RADIUS * acos; // 最终结果
    }



    // 调用百度地图API根据地址，获取坐标
    public String getCoordinate(String address) {
//        System.out.println("coor" + address);
        if (address != null && !"".equals(address)) {
            address = address.replaceAll("\\s*", "").replace("#", "栋");
            String url = "http://api.map.baidu.com/geocoder/v2/?address=" + address + "&output=json&ak=" + AK;
            String json = loadJSON(url);
            if (json != null && !"".equals(json)) {
                JSONObject obj = JSONObject.fromObject(json);
                if ("0".equals(obj.getString("status"))) {
                    double lng = obj.getJSONObject("result").getJSONObject("location").getDouble("lng"); // 经度
                    double lat = obj.getJSONObject("result").getJSONObject("location").getDouble("lat"); // 纬度
                    DecimalFormat df = new DecimalFormat("#.######");
//                    System.out.println(df.format(lng) + "," + df.format(lat));
                    return df.format(lng) + "," + df.format(lat);
                }
            }
        }
        return null;
    }

    public  String loadJSON(String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {} catch (IOException e) {}
        return json.toString();
    }

}
