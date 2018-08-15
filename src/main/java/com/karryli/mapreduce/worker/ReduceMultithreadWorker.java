package com.karryli.mapreduce.worker;

/**
 * Created by yuxiao on 8/10/18.
 */

import com.karryli.mapreduce.reduce.Reducer;

/**
 * @author: danielpo
 * Date: 7/10/13
 * Time: 11:32 AM
 */
public class ReduceMultithreadWorker extends ReduceWorkerWrapper {

    protected ReduceMultithreadWorker(Reducer reducer, long workerId) {
        super(reducer, workerId);
    }

    protected ReduceMultithreadWorker(Reducer reducer) {
        super(reducer);
    }

    @Override
    public void run() {

    }


    public void begin() {
    }



    public Object call() throws Exception {
        return null;
    }
}