package com.karryli.mapreduce.worker;

import java.util.List;
import java.util.Random;

/**
 * Created by yuxiao on 8/10/18.
 */
public abstract class BaseWorker extends Thread implements Worker {

    public long workerId;
    boolean isRunning;
    boolean taken;
    Object input;
    List results;

    public Integer genWorkerId(){
        Random random = new Random();
        return random.nextInt(40)+1;
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public long getWorkerId() {
        return workerId;
    }



    public void setRunning(boolean running) {
        this.isRunning = running;
    }

    public void setInput(Object input) {
        this.input = input;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public boolean isTaken() {
        return this.taken;
    }

    public void setResults(List<String> results) {
        this.results = results;
    }


    public void init(){
        this.workerId = genWorkerId();
    }

}
