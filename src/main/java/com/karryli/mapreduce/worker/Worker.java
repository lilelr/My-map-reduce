package com.karryli.mapreduce.worker;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by yuxiao on 8/10/18.
 */
public interface Worker extends Runnable, Callable {

    public boolean isRunning();

    public long getWorkerId();

    public void begin();

    public void setRunning(boolean running);

    public void setInput(Object input);

    public void setTaken(boolean taken);

    public boolean isTaken();

    public void setResults(List<String> results);


}
