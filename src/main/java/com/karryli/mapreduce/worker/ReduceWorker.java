package com.karryli.mapreduce.worker;

import com.karryli.mapreduce.reduce.Reducer;

/**
 * Created by yuxiao on 8/10/18.
 */
public interface ReduceWorker {

    public void setReducer(Reducer reducer);
}
