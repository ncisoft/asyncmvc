package com.mobiscloud;

import static org.junit.Assert.*;

import com.mobiscloud.asyncmvc.MethodContext;
import org.junit.Test;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedWriter;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    private static Log _log = LogFactory.getLog(AppTest.class);

    @Test
    public void testSsApp()
    {
        _log.info("...");
        MethodContext ctx = new MethodContext();
        _log.debug(ctx.getElapsed() );
        _log.debug(ctx.getElapsed() );
        _log.debug("method call -- end -- " + ctx.getElapsed() + " ms");
        _log.debug("method call -- end -- " + ctx.getElapsed() + " ms");
        _log.info(";llll");
        _log.debug("method call -- end -- " + ctx.getElapsed() + " ms");
        _log.debug( "method call -- end -- " + ctx.getElapsed() + " ms");
        _log.debug(ctx.getElapsed() );
        _log.debug(ctx.getElapsed() );
        assertTrue(true);
    }


}
