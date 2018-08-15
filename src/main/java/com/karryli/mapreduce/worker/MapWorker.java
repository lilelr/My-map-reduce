package com.karryli.mapreduce.worker;

import com.karryli.mapreduce.map.Mapper;

/**
 * Created by yuxiao on 8/10/18.
 */
public interface MapWorker extends Worker{

    public void setMapper(Mapper mapper);
}
