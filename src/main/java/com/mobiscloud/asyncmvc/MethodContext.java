package com.mobiscloud.asyncmvc;

import java.text.DecimalFormat;

/**
 * Created by leeyg on 15-11-19.
 */
public class MethodContext {
    long startTime;
    private static DecimalFormat format = new DecimalFormat();

    public MethodContext() {
        startTime = System.nanoTime();
    }

    /**
     *
     * @return elapse time(ns) since the object was create
     */
    public String getElapsed()
    {
        long elapsed = System.nanoTime()-startTime;;
        return format.format((elapsed/1000000d));
    }
}
