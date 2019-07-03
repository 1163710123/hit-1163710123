package com.experiment.tool;

import com.experiment.mapper.HourseMapper;
import com.experiment.pojo.hourse;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/4/004.
 */
public class FindSurroundHouse
{
    public List<hourse> getNearDistance(double lng, double lat, List<hourse> hourses){
        Location location = new Location();
        List<hourse> result = new LinkedList<>();
//        System.out.println(hourses.toString());
        for(hourse h : hourses){
//            System.out.println(h.getH_address());
            double distance = location.getDistance(lng,lat,h.getH_address());
            System.out.println(h.getH_address() + distance);
            if(distance <= 10000)
            {
                result.add(h);
            }
        }
        return result;
    }
}
