package com.experiment.tool;

import com.experiment.mapper.ScanMapper;
import com.experiment.pojo.house;
import com.experiment.pojo.scan;
import com.experiment.pojo.user;
import com.experiment.utils.Sql;
import org.apache.ibatis.session.SqlSession;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2019/6/15/015.
 */
public class Recommend
{
    private static int Row = 10;
    //获得推荐排序的房源列表
    public  int[] getRecommend(List<scan> scan,float[][] similarity){
        float[] score = new float[Row];
        //分数数组
        for(int i = 0;i < Row;i++){
            for(scan s : scan){
                if(s.getH_id() == i+1){
                    score[i] = s.getScore();
                    break;
                }
            }
        }
        //推荐评分
        float[] temp_out = new float[Row];
        for(int i = 0;i < Row;i++){
            for (int j = 0;j < Row; j++){
                temp_out[i] += score[j] * similarity[j][i];

            }
        }
        return Arraysort(temp_out);
    }

    //计算房源间相似度
    public float[][] similarity(){
        File file = new File("D:\\Javacx\\shiyan3cxf\\src\\main\\com\\experiment\\data\\my_house.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 0;
            float[][] house_data= new float[Row][8];
//            List<Float[]> house_data = new ArrayList<>();
            //读入数组
            while ((tempString = reader.readLine()) != null) {
                String[] temp = tempString.split("  ");
                for (int i = 0;i < temp.length;i++){
                    house_data[line][i] = Float.parseFloat(temp[i]);
//                    System.out.println(house_data[line][i]);
                }
                line++;
            }
            reader.close();
            //数据归一化
            float[][] nor_house = normalize4Scale(house_data);
            float[][] similarity = new float[Row][Row];
            DecimalFormat df = new DecimalFormat(".000");
            for(int i = 0;i < Row;i++){
                for (int j = i;j < Row;j++){
                    similarity[i][j] =  Float.parseFloat(df.format(1 - caldist(nor_house,i,j,8)));
                    similarity[j][i] = similarity[i][j];
                }
            }

            System.out.println("数据归一化");
            for(int i = 0;i < Row;i++){
                for (int j =0;j < 8;j++){
                    System.out.print(nor_house[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("相似度矩阵");
            for(int i = 0;i < Row;i++){
                for (int j =0;j < Row;j++){
                    System.out.print(similarity[i][j] + " ");
                }
                System.out.println();
            }
            return similarity;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getUserSimilarity(int u_id,List<user> users){
        int N = users.size();
        float[][] user_info = new float[N][3];
        Calendar c1 = Calendar.getInstance();
        int year_now = c1.get(Calendar.YEAR);
        Location location = new Location();
        DecimalFormat df = new DecimalFormat(".000");
        for (int i = 0; i < N; i++){
            user u = users.get(i);
            //性别数据化
            if(u.getU_sex().equals("女")){
                user_info[i][0] = 0;
            }else{
                user_info[i][0] = 1;
            }
            //生日数据化
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = sdf.format(u.getU_birth());
            int year = Integer.parseInt(dateStr.split("-")[0]);
            user_info[i][1] = Math.abs(year_now - year);
            //地址数据化
            user_info[i][2] = Float.parseFloat(df.format(location.getDistance(u.getU_address(),"哈尔滨中央大街")/1000));
        }
        for(int i = 0;i < N;i++){
            for (int j =0;j < 3;j++){
                System.out.print(user_info[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("用户矩阵归一化");
        float[][] nor_user = normalize4Scale(user_info);
        for(int i = 0;i < N;i++){
            for (int j =0;j < 3;j++){
                System.out.print(nor_user[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        float[][] user_similarity = new float[N][N];
        float[] u_sim = new float[N];
        for(int i = 0;i < N;i++){
            for (int j = i;j < N;j++){
//                System.out.println(df.format(caldist_user(nor_user,i,j,3)));
                user_similarity[i][j] =  Float.parseFloat(df.format(caldist_user(nor_user,i,j,3)));
                user_similarity[j][i] = user_similarity[i][j];
            }
        }
        System.out.println("用户相似度矩阵");
        for(int i = 0;i < N;i++){
            for (int j =0;j < 3;j++){
                System.out.print(user_similarity[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0;i < N;i++){
            u_sim[i] = user_similarity[u_id-1][i];
//            System.out.print(u_sim[i]);
        }
//        System.out.println();
        return Arraysort(u_sim)[1] + 1;
    }

    //计算房源两行间的相似度
    public float caldist(float[][] data,int row1,int row2,int n){
        float sum = 0;
//        System.out.println("house1 = " + (row1 + 1) + " house2 = " + (row2+1) + "sum ");
        for(int i = 0;i < n;i++){
            if (i == 4 || i == 5){
                sum += 0.03 * (data[row1][i] - data[row2][i]) * (data[row1][i] - data[row2][i]);
            }else if(i == 3){
                sum += 0.3 * (data[row1][i] - data[row2][i]) * (data[row1][i] - data[row2][i]);
            }else if(i == 7){
                sum += 0.4 * (data[row1][i] - data[row2][i]) * (data[row1][i] - data[row2][i]);
            }else{
                sum += 0.06 * (data[row1][i] - data[row2][i]) * (data[row1][i] - data[row2][i]);
            }
//            System.out.print(sum + "  ");
        }
//        System.out.println("house1 = " + (row1 + 1) + " house2 = " + (row2+1) + "   " + sum);
        if(sum == 0){
            return 0;
        }else {
            return (float) Math.sqrt(sum);
        }
    }

    public float caldist_user(float[][] data,int row1,int row2,int n){
//        System.out.println("house1 = " + (row1 + 1) + " house2 = " + (row2+1) + "sum ");
        float add = 0;
        float square_row1 = 0;
        float square_row2 = 0;
        for(int i = 0;i < n;i++){
            square_row1 += data[row1][i] * data[row1][i];
            square_row2 += data[row2][i] * data[row2][i];
            add += data[row1][i] * data[row2][i];
        }
        float cos = (float) (Math.abs(add)/(Math.sqrt(square_row1) * Math.sqrt(square_row2)));
//        System.out.println("cos = " + cos);
        return cos;
    }

    //矩阵归一化
    public float[][] normalize4Scale(float[][] points) {
        if (points == null || points.length < 1) {
            return points;
        }
        float[][] p = new float[points.length][points[0].length];
        float[] matrixJ;
        float maxV;
        float minV;
        for (int j = 0; j < points[0].length; j++) {
            matrixJ = getMatrixCol(points, j);
            maxV = maxV(matrixJ);
            minV = minV(matrixJ);
            DecimalFormat df = new DecimalFormat(".000");
            for (int i = 0; i < points.length; i++) {
                p[i][j] = Float.parseFloat(df.format(maxV == minV ? minV : (points[i][j] - minV) / (maxV - minV)));
            }
        }
        return p;
    }

    /**
     * 获取矩阵的某一列
     *
     * @param points points
     * @param column column
     * @return double[]
     */
    public float[] getMatrixCol(float[][] points, int column) {
        float[] matrixJ = new float[points.length];
        for (int i = 0; i < points.length; i++) {
            matrixJ[i] = points[i][column];
        }
        return matrixJ;
    }

    /**
     * 获取数组中的最小值
     *
     * @param matrixJ matrixJ
     * @return v
     */
    public float minV(float[] matrixJ) {
        float v = matrixJ[0];
        for (int i = 0; i < matrixJ.length; i++) {
            if (matrixJ[i] < v) {
                v = matrixJ[i];
            }
        }
        return v;
    }

    /**
     * 获取数组中的最大值
     *
     * @param matrixJ matrixJ
     * @return v
     */
    public float maxV(float[] matrixJ)
    {
        float v = matrixJ[0];
        for (int i = 0; i < matrixJ.length; i++)
        {
            if (matrixJ[i] > v)
            {
                v = matrixJ[i];
            }
        }
        return v;
    }
//    public  void main(String[] args)
//    {
//        float[][] a = similarity();
//        SqlSession s = Sql.getSql();
//        List<scan> scan = s.getMapper(ScanMapper.class).getscan(1);
//        getRecommend(scan,a);
//    }

    public int[] Arraysort(float[]arr)
    {
        //double[] arr = {5.5,2,66,3,7,5};
        float temp;
        int index;
        int k=arr.length;
        int[]Index= new int[k];
        for(int i=0;i<k;i++)
        {
            Index[i]=i;
        }

        for(int i=0;i<arr.length;i++)
        {
            for(int j=0;j<arr.length-i-1;j++)
            {
                if(arr[j]<arr[j+1])
                {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;

                    index=Index[j];
                    Index[j] = Index[j+1];
                    Index[j+1] = index;
                }
            }
        }
        return Index;
    }
}
