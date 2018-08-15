package com.karryli.mapreduce.master;

/**
 * Created by yuxiao on 8/10/18.
 */
public interface Master {

    public void begin();
    public Object reduceResults();
}
