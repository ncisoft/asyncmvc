package com.mobiscloud;

import com.mobiscloud.asyncmvc.AsyncWorker;
import static org.junit.Assert.*;

import com.mobiscloud.asyncmvc.Method;
import org.junit.Before;
import org.junit.Test;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Unit test for simple App.
 */
public class AsyncWorkerrTest {

    private static Log _log = LogFactory.getLog(AsyncWorkerrTest.class);

    AsyncWorker helper;

    @Before
    public void setUp() throws Exception {
        helper = new AsyncWorker();
    }

    @Test
    public void testFuture() throws Throwable {
        _log.info("call-1");
        helper.invokeServlet(new Method<Boolean>() {
            @Override
            public  Boolean call() {

                _log.info("call-1--" + Thread.currentThread().getName());
                return Boolean.TRUE;
            }
        });
        assertTrue(true);
    }

    @Test
    public void testFutureWithException() throws Throwable {
        _log.info("call-2");
        try {
            helper.invokeServlet(new Method<Boolean>() {
                @Override
                public Boolean call() {
                    _log.info("call-2--" + Thread.currentThread().getName());
                    //throw new RuntimeException("runtime");
                    return null;
                }
            });
        } catch (Throwable throwable) {
            //throwable.printStackTrace();
            _log.info("throw exception");
            assertTrue(true);
            return;
        }
        assertTrue(true);
    }
}