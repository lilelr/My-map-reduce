package com.karryli.mapreduce.master.exception;

/**
 * Created by yuxiao on 8/10/18.
 */
public class MasterException extends RuntimeException{

    public MasterException(){
        super();
    }

    public MasterException(String message){
        super(message);
    }

    public MasterException(String message, Throwable cause){
        super(message, cause);
    }

    public MasterException(Throwable cause){
        super(cause);
    }


}
