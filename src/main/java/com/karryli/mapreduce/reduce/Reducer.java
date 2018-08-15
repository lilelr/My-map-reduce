package com.karryli.mapreduce.reduce;

import java.util.Collection;

/**
 * Created by yuxiao on 8/10/18.
 */
public interface Reducer {
    public Object reduce(Collection c);

}
