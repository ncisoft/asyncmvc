package com.mobiscloud.servlet;

import com.mobiscloud.asyncmvc.AsyncWorker;
import com.mobiscloud.asyncmvc.Method;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.*;

// Miscellaneous:
import java.io.*;

// import java.io.BufferedReader;

//import com.ibm.servlet.PageListServlet;

public class leeyg extends HttpServlet implements Serializable
//public class web.leeyg extends DerivableServlet implements Serializable
{
    AsyncWorker helper;

    private static Log _log = LogFactory.getLog(leeyg.class);


    /**
     * Handle the HTTP GET method by building a simple web page.
     */


    public void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
                PrintWriter out;

                // set content type and other response header fields first
                res.setContentType("text/html; charset=UTF-8");
                // then write the data of the response
                out = res.getWriter();

                out.println("<html><body>");
                out.println("<br>" + hello());
                out.println("</body></html>");
                out.close();
                _log.info("invoke service()--traditional mode");
    }

    public String helloAsync() {
        return "hello, young lee李毅刚！！！Async";
    }

    public String hello() {
        return "hello, young lee李毅刚！！！";
    }
}


