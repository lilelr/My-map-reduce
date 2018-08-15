package com.karryli.mapreduce.worker;

import com.karryli.mapreduce.MapStage;
import com.karryli.mapreduce.map.Mapper;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Created by yuxiao on 8/10/18.
 */
public class MapThreadWorker extends MapWorkerWrapper {


    public MapThreadWorker(Mapper mapper, Object input, List results) {
        super(mapper, input, results);
    }

    @Override
    public void run() {
        super.run();
        process();
    }

    public void process(){
        System.out.println("Thread start: "+genWorkerId());
//        System.out.println("Processing: "+input);
        MapStage mapPhase = new MapStage(mapper,input);
        results.add(mapPhase.map());

    }

    public void begin() {
        System.out.println("Thread start :" + getWorkerId());
        System.out.println("Processing : " + input);
        start();
    }

    public Object call() throws Exception {
        process();
        return results;    }
}
