package com.mobiscloud.servlet;

import com.mobiscloud.asyncmvc.AsyncWorker;
import com.mobiscloud.asyncmvc.Method;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

// Miscellaneous:

// import java.io.BufferedReader;

//import com.ibm.servlet.PageListServlet;

public class leeygasync extends HttpServlet implements Serializable
//public class web.leeyg extends DerivableServlet implements Serializable
{
    private static AsyncWorker helper= new AsyncWorker();
    private static Log _log = LogFactory.getLog(leeygasync.class);

    /**
     * Handle the HTTP GET method by building a simple web page.
     */

    public String hello() {
        return "hello, ASYNC lee李毅刚！！！";
    }

    public void service(final HttpServletRequest req, final HttpServletResponse res)
            throws ServletException, IOException {
        _log.info("helper==null? " + (helper == null));
        helper.invokeServlet(new Method<Boolean>() {
            public Boolean call() throws Exception {
                PrintWriter out;

                // set content type and other response header fields first
                res.setContentType("text/html; charset=UTF-8");
                // then write the data of the response
                out = res.getWriter();

                out.println("<html><body>");
                out.println("<br>" + hello());
                out.println("</body></html>");
                out.close();
                _log.info("invoke service()");
                return  null;
            }
        });//Inner<Boolean>(req, res));
    }

    public class MethodInner<T> extends Method<T> {
        final HttpServletRequest req;
        final HttpServletResponse res;


        public MethodInner(HttpServletRequest req, HttpServletResponse res) {
            this.req = req;
            this.res = res;
        }

        public T call() throws Exception {
            PrintWriter out;

            try {
                // set content type and other response header fields first
                res.setContentType("text/html; charset=UTF-8");
                // then write the data of the response
                out = res.getWriter();

                out.println("<html><body>");
                out.println("<br>" + hello());
                out.println("</body></html>");
                out.close();
                _log.info("invoke service()");
                return  null;
            } catch (IOException e) {
                throw new RuntimeException(e);
        }

        }
    }
}





