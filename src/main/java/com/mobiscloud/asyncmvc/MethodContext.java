package com.mobiscloud.asyncmvc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DecimalFormat;

/**
 * Created by leeyg on 15-11-19.
 */
public class MethodContext {
    long startTime;
    private static DecimalFormat format = new DecimalFormat();
    private static Log _log = LogFactory.getLog(MethodContext.class);

    public MethodContext() {
        startTime = System.nanoTime();
        this.getElapsed();
        startTime = System.nanoTime();
    }

    /**
     *
     * @return elapse time(ns) since the object was create
     */
    public String getElapsed()
    {
        long elapsed = System.nanoTime()-startTime;

        return format.format((elapsed/1000000d));
    }
}
