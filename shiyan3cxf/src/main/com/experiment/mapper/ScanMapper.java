package com.experiment.mapper;

import com.experiment.pojo.hourse;
import com.experiment.pojo.scan;

import java.util.List;

/**
 * Created by Administrator on 2019/6/14/014.
 */
public interface ScanMapper
{
    public List<scan> getscan(Integer id);

    public void insertscan(scan scan);

    public void updatescan(scan scan);

    public scan getscanbyid(int u_id,int h_id);
}
