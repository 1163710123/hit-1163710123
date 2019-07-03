package com.experiment.mapper;

import com.experiment.pojo.hourse;

import java.util.List;

public interface HourseMapper {
    /**
     * d得到所有的二手房信息
     */
    public List<hourse> getallhourses();
    /**
     * 更新二手房信息
     * @param hourse
     */
    public void updatehourse(hourse hourse);
    /**
     * 添加二手房
     * @param hourse
     */
    public void insertHourse(hourse hourse);

    /**
     * 删除二手房
     * @param id
     */
    public void deleteHourse(Integer id);


    public List<hourse> choosehourse(Integer id);

    public List<hourse> getuserhourse(Integer id);

    public List<hourse> getlendhourse(Integer id);


}
