package com.mobiscloud;

import com.mobiscloud.asyncmvc.AsyncWorker;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Unit test for simple App.
 */
public class AsyncHelperTest{

    private static Log _log = LogFactory.getLog(AsyncHelperTest.class);

    AsyncWorker helper;

    @Before
    public void setUp() throws Exception {
        helper = new AsyncWorker();
    }

    @Test
    public void testFuture() throws Throwable {
        _log.info("call-1");
        helper.invokeServlet(new Runnable() {
            @Override
            public void run() {

                _log.info("call-1--" + Thread.currentThread().getName());
            }
        });
        assertTrue(true);
    }

    @Test
    public void testFutureWithException() throws Throwable {
        _log.info("call-2");
        try {
            helper.invokeServlet(new Runnable() {
                @Override
                public void run() {
                    _log.info("call-2--" + Thread.currentThread().getName());
                    throw new RuntimeException("runtime");
                }
            });
        } catch (Throwable throwable) {
            //throwable.printStackTrace();
            assertTrue(true);
            return;
        }
        assertTrue(false);
    }
}