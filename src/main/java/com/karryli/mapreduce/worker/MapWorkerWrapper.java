package com.karryli.mapreduce.worker;

import com.karryli.mapreduce.map.Mapper;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Created by yuxiao on 8/10/18.
 */
public abstract class MapWorkerWrapper extends BaseWorker implements MapWorker{

    Mapper mapper;

    BlockingQueue<Worker> threads;

    protected MapWorkerWrapper(final Mapper mapper, final Object input, final List results) {
        this.mapper = mapper;
        this.input = input;
        this.results = results;
        super.init();
    }

//    protected MapWorkerWrapper(final Mapper mapper, final Object input, final List results) {
//        this.mapper = mapper;
//        this.input = input;
//        this.results = results;
////        this.threads = threads;
//        super.init();
//    }

    protected MapWorkerWrapper(Mapper mapper, long workerId) {
        this.mapper = mapper;
        this.workerId = workerId;
    }

    protected MapWorkerWrapper(Mapper mapper) {
        this.mapper = mapper;
        super.init();
    }


    public Mapper getMapper() {
        return mapper;
    }

    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    public synchronized void addToThreads(Worker worker){
        try {
            this.threads.put(worker);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void removeFromThreads(Worker worker){
        this.threads.remove(worker);
        this.threads.notify();
    }
}
