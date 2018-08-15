package com.karryli.mapreduce.master;

import com.karryli.mapreduce.ReduceStage;
import com.karryli.mapreduce.map.Mapper;
import com.karryli.mapreduce.reduce.Reducer;
import com.karryli.mapreduce.worker.MapThreadWorker;
import com.karryli.mapreduce.worker.MapWorker;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by yuxiao on 8/10/18.
 */
public class MasterWorkers extends MasterWrapper {

    private List<String> values = Collections.synchronizedList(new LinkedList<String>());

    private Object reduceResults;


    public MasterWorkers(List input, Mapper mapper, Reducer reducer) {
        super(input, mapper, reducer);
    }

    public MasterWorkers(Reducer reducer, Mapper mapper, List input, int workersNo) {
        super(reducer, mapper, input, workersNo);
    }

    public void begin() {
        if(workersNo==0) workersNo = input.size();
        ExecutorService threadPool = Executors.newFixedThreadPool(workersNo) ;
        System.out.println("threadPool workderNo: "+workersNo);
        CompletionService pool = new ExecutorCompletionService(threadPool);
        for (Object s : input) {
            MapWorker worker = new MapThreadWorker(mapper, s, values);
            pool.submit(worker);
        }


        threadPool.shutdown();

        try {
            boolean finished = threadPool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        ReduceStage rp = new ReduceStage(values, reducer);
        reduceResults = rp.reduce();
    }

    public Object reduceResults() {
        return reduceResults;
    }

}
