package com.mobiscloud.asyncmvc;

import java.util.concurrent.Callable;

/**
 * Created by leeyg on 15-11-18.
 */
public abstract class Method<V> implements Callable<V> {
    MethodContext methodContext = new MethodContext();

    public MethodContext getMethodContext() {
        return methodContext;
    }

    /**
     *
     * @return elapse time(ns) since the object was create
     */
    public String getElapsed()
    {
        return methodContext.getElapsed();
    }

    public abstract V call() throws Exception;
}
