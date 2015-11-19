package com.mobiscloud.asyncmvc.struts;

import com.mobiscloud.asyncmvc.AsyncWorker;
import com.mobiscloud.asyncmvc.Method;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by leeyg on 15-11-16.
 */
public class AsyncActionServlet extends ActionServlet {
    private static Log _log = LogFactory.getLog(AsyncActionServlet.class);
    private static AsyncWorker _asyncWorker;
    private static Integer _for_lock = new Integer(0);

    private void initWorker() {
        synchronized (_for_lock) {
            if (_asyncWorker == null)
                _asyncWorker = new AsyncWorker();
        }

    }

    protected void _process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        super.process(request, response);
    }

    @Override
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        initWorker();
        _asyncWorker.invokeServlet(new Method<Boolean>() {
            @Override
            public Boolean call() {
                try {
                    _process(request, response);
                    return Boolean.TRUE;

                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ServletException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        initWorker();
        final ActionServlet _super = (ActionServlet)this;


        _asyncWorker.invokeServlet(new Method<Boolean>() {
            @Override
            public Boolean call() {
                try {
                    _super.doGet(request, response);
                    _process(request, response);
                    return null;

                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ServletException e) {
                    throw new RuntimeException(e);
                }
            }
        });


    }
}

