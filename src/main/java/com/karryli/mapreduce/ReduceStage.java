package com.karryli.mapreduce;

import com.karryli.mapreduce.reduce.Reducer;

import java.util.List;

/**
 * Created by yuxiao on 8/10/18.
 */
public class ReduceStage {

    List mapResult;

    Reducer reducer;

    public ReduceStage(List mapResult, Reducer reducer){
        this.mapResult = mapResult;
        this.reducer = reducer;
    }

    public Object reduce(){
        // 执行reduce 操作
        return reducer.reduce(mapResult);
    }
}
