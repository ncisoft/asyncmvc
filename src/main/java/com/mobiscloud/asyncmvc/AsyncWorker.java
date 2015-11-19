package com.mobiscloud.asyncmvc;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by leeyg on 15-11-16.
 */
public class AsyncWorker {
    private static ExecutorService _servletThreadPool;
    private static ExecutorService _actionThreadPool;
    private static AtomicInteger countTransaction  = new AtomicInteger(0);
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

    private String getTransId(final int id)
    {
        StringBuilder stringBuilder = new StringBuilder(10);

        return stringBuilder.append("[tx::")
                .append(id)
                .append("] ")
                .toString();
    }
    public void invokeServlet(final Method<Boolean> m) {
        invoke(_servletThreadPool, m);
    }

    public void invokeAction(final Method<Boolean> m) {
        invoke(_actionThreadPool, m);
    }
    public void invoke(ExecutorService threadPool, final Method<Boolean> m) {
        final  int transId = countTransaction.getAndIncrement();

        _log.info(getTransId(transId) +"async method call -- begin -- " + m.getElapsed() + " ms");


        try {
            if (false) {
                m.call();
            }
            else {
                Future<Boolean> future = threadPool.submit(m);
                future.get();
            }
        } catch (InterruptedException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
           _log.info(getTransId(transId)+ "async method call -- end -- " + m.getElapsed() + " ms");
        }
    }

    private class SubmitClass<T> implements Callable<T>
    {
        Method m;

        public SubmitClass(Method m) {
            this.m = m;
        }

        public T call() throws Exception {
            //_log.info(getTransId(transId) + Thread.currentThread().getName() + " -- ");
            m.call();
            return null;

        }
    }
    public static void main(String args[]) {
        _log.info("iiii");
        AsyncWorker helper = new AsyncWorker();
    }

}
