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
                    .setNameFormat("asyncmvc-servlet-pool-%d")
                    .build();

            _actionThreadPool = Executors.newSingleThreadExecutor(namedThreadFactory);
            namedThreadFactory = new ThreadFactoryBuilder()
                    .setNameFormat("asyncmvc-action-pool-%d")
                    .build();
            _servletThreadPool = Executors.newFixedThreadPool(5, namedThreadFactory); // newCachedThreadPool
            assert (_servletThreadPool != null);
            assert (_actionThreadPool != null);
            _log.debug("AsyncWorker init done");
        };
    }

    public void invokeServlet(final Runnable f) throws Throwable {
        invoke(_servletThreadPool, f);
    }

    public void invokeAction(final Runnable f) throws Throwable {
        invoke(_actionThreadPool, f);
    }
    public void invoke(ExecutorService threadPool, final Runnable f) throws Throwable {
        Future<Boolean> future  = threadPool.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                _log.info(Thread.currentThread().getName());
                f.run();
                return Boolean.TRUE;
            }
        });

        try {
            future.get();
        } catch (InterruptedException e) {
            //e.printStackTrace();
            throw e;
        } catch (ExecutionException e) {
            //e.printStackTrace();
            throw e;
        }
        _log.debug("future done");
    }

    public static void main(String args[]) {
        System.out.println("iiii");
        AsyncWorker helper = new AsyncWorker();
    }

}
