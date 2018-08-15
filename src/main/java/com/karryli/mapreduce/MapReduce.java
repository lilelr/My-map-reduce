package com.karryli.mapreduce;

import com.karryli.mapreduce.map.Mapper;
import com.karryli.mapreduce.master.Master;
import com.karryli.mapreduce.master.MasterWorkers;
import com.karryli.mapreduce.reduce.Reducer;

import java.util.List;

/**
 * Created by yuxiao on 8/10/18.
 */
public class MapReduce {

    private Object input;
    private Object output;
    Mapper mapper;
    Reducer reducer;
    int workersNo;

    private MapReduce(final Builder builder) {
        this.input = builder.input;
        this.output = builder.output;
        this.mapper = builder.mapper;
        this.reducer = builder.reducer;
        this.workersNo = builder.workersNo;
    }

    public static class Builder{
        Object input;
        Object output;
        Mapper mapper;
        Reducer reducer;
        int workersNo;

        public Builder input(Object input) {
            this.input = input;
            return this;
        }

        public Builder output(Object output) {
            this.output = output;
            return this;
        }

        public Builder mapper(Mapper mapper) {
            this.mapper = mapper;
            return this;
        }

        public Builder reducer(Reducer reducer) {
            this.reducer = reducer;
            return this;
        }

        public Builder workersNo(int workersNo) {
            this.workersNo = workersNo;
            return this;
        }

        public MapReduce build() {
            return new MapReduce(this);
        }
    }

    public Master master = null;


    /**
     * this method delegates the work to MasetrWork class that manages the entire com.karryli.mapreduce.map-com.karryli.mapreduce.reduce process.
     */
    public void begin() {
        master = new MasterWorkers(reducer, mapper, (List) input, workersNo);
        master.begin();
    }
}
