package com.karryli.mapreduce.master;

import com.karryli.mapreduce.map.Mapper;
import com.karryli.mapreduce.reduce.Reducer;
import com.karryli.mapreduce.worker.MapWorkerWrapper;
import com.karryli.mapreduce.worker.ReduceWorkerWrapper;
import com.karryli.mapreduce.worker.Worker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by yuxiao on 8/10/18.
 */
public abstract class MasterWrapper implements Master{


    ArrayList<MapWorkerWrapper> workers = new ArrayList<MapWorkerWrapper>();
//    protected BlockingQueue<Worker> threads;
    ArrayList<ReduceWorkerWrapper> reduceWorkers = new ArrayList<ReduceWorkerWrapper>();

    List input;
    Mapper mapper;
    Reducer reducer;
    int workersNo;

    protected MasterWrapper(List input, Mapper mapper, Reducer reducer) {
        this.input = input;
        this.mapper = mapper;
        this.reducer = reducer;
        // init();
    }

    protected MasterWrapper(Reducer reducer, Mapper mapper, List input, int workersNo) {
        this.reducer = reducer;
        this.mapper = mapper;
        this.input = input;
        this.workersNo = workersNo;
        //init();
    }

    public void registerWorker(MapWorkerWrapper abstractMapWorker){
        workers.add(abstractMapWorker);
    }

    public void deleteWorker(MapWorkerWrapper abstractMapWorker){
        workers.remove(abstractMapWorker);
    }



}
