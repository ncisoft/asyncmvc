package com.mobiscloud.asyncmvc;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import java.util.concurrent.*;

/**
 * Created by leeyg on 15-11-16.
 */
public class AsyncWorker {
    private static ExecutorService _servletThreadPool;
    private static ExecutorService _actionThreadPool;
    private static Log _log = LogFactory.getLog(AsyncWorker.class);

    public AsyncWorker() {
        if (_servletThreadPool == null) {
            ThreadFactory namedThreadFactory;
            namedThreadFactory = new ThreadFactoryBuilder()
                    .setNameFormat("asyncworker-servlet-pool-%d")
                    .build();

            _actionThreadPool = Executors.newSingleThreadExecutor(namedThreadFactory);
            namedThreadFactory = new ThreadFactoryBuilder()
                    .setNameFormat("asyncworker-action-pool-%d")
                    .build();
            _servletThreadPool = Executors.newFixedThreadPool(5, namedThreadFactory); // newCachedThreadPool
            assert (_servletThreadPool != null);
            assert (_actionThreadPool != null);
            _log.debug("AsyncWorker init done");
        };
    }

    public void invokeServlet(Method m){
        invoke(_servletThreadPool, m);
    }

    public void invokeAction(final Method m){
        invoke(_actionThreadPool, m);
    }
    public void invoke(ExecutorService threadPool, final Method m) {
        Future<Boolean> future  = threadPool.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                _log.info(Thread.currentThread().getName());
                m.call();
                return Boolean.TRUE;
            }
        });

        try {
            future.get();
        } catch (InterruptedException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        }
        _log.debug("future done");
    }

    public static void main(String args[]) {
        _log.info("iiii");
        AsyncWorker helper = new AsyncWorker();
    }

}
