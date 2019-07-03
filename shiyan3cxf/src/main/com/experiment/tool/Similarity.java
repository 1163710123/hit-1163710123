package com.experiment.tool;

import com.experiment.pojo.hourse;
import com.experiment.service.ServiceImpl;

import java.util.*;

/**
 * Created by Administrator on 2019/3/26/026.
 */
public class Similarity
{
    private static final double INF = 1000000000;


    public LinkedHashMap<Integer,Double> min_MaxNormalizationData(LinkedHashMap<Integer,Double> input){
        double max = 0;
        double min = INF;
        for(double data : input.values()){
            if(data > max){
                max = data;
            }
            if(data < min){
                min = data;
            }
        }
        LinkedHashMap<Integer,Double> outcomes = new LinkedHashMap<>();
        for(double data : input.values()){
            double normalization = (data - min)/(max - min);
            for(int i : input.keySet()){
                if(input.get(i) == data){
                    outcomes.put(i,normalization);
                }
            }
//            System.out.println(normalization);
        }
//        System.out.println(outcomes);
        return outcomes;
    }

    public Map<Integer,Double> getSimilarHouse(List<hourse> houses,hourse house){
        Location location = new Location();
        LinkedHashMap<Integer,Double> dis = new LinkedHashMap<>();
        LinkedHashMap<Integer,Double> pri = new LinkedHashMap<>();
        for(hourse h : houses){
            double distance = location.getDistance(house.getH_address(),h.getH_address());
            double price = Math.abs(house.getH_price() - h.getH_price());
            dis.put(h.getH_id(),distance);
            pri.put(h.getH_id(),price);
            System.out.println(distance + "      " + price);
        }
        dis = min_MaxNormalizationData(dis);
        pri = min_MaxNormalizationData(pri);
        LinkedHashMap<Integer,Double> similarity = new LinkedHashMap<>();

        for(hourse h : houses){
            double dis_i = dis.get(h.getH_id());
            double pri_i = pri.get(h.getH_id());
            double sim = Math.sqrt(dis_i * dis_i + pri_i * pri_i);
            similarity.put(h.getH_id(),sim);
        }
        Map<Integer,Double> result = sortByValueAscending(similarity);
        System.out.println(result.toString());
        return result;
    }

    //升序排序
    public static <Integer, Double extends Comparable<? super Double>> Map<Integer, Double> sortByValueAscending(Map<Integer, Double> map)
    {
        List<Map.Entry<Integer, Double>> list = new LinkedList<Map.Entry<Integer, Double>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Double>>()
        {
            @Override
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2)
            {
                int compare = (o1.getValue()).compareTo(o2.getValue());
                return compare;
            }
        });

        Map<Integer, Double> result = new LinkedHashMap<Integer, Double>();
        for (Map.Entry<Integer, Double> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

//    public static void main(String[] args)
//    {
//        ServiceImpl service = new ServiceImpl();
//        Similarity similiarity = new Similarity();
//        similiarity.getSimilarHouse(service.getallHourse(),service.choosehourse(2).get(0));
//    }
}
