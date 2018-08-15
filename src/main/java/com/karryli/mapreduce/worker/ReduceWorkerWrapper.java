package com.karryli.mapreduce.worker;

import com.karryli.mapreduce.reduce.Reducer;

/**
 * Created by yuxiao on 8/10/18.
 */
public abstract class ReduceWorkerWrapper extends BaseWorker implements ReduceWorker{

    Reducer reducer;

    protected ReduceWorkerWrapper(Reducer reducer, long workerId) {
        this.reducer = reducer;
        this.workerId = workerId;
    }

    protected ReduceWorkerWrapper(Reducer reducer) {
        this.reducer = reducer;
        this.workerId = genWorkerId();
    }

    public Reducer getReducer() {
        return reducer;
    }

    public void setReducer(Reducer reducer) {
        this.reducer = reducer;
    }
}