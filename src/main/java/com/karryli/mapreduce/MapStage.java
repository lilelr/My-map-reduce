package com.karryli.mapreduce;

import com.karryli.mapreduce.map.Mapper;

/**
 * Created by yuxiao on 8/10/18.
 */
public class MapStage {

    private Mapper mapper;
    Object input;

    public MapStage(Mapper mapper, Object input){
        this.mapper =mapper;
        this.input = input;
    }


    public Object map(){
        return mapper.map(input);
    }
}
