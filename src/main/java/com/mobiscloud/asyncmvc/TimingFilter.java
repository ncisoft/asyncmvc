package com.mobiscloud.asyncmvc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Created by leeyg on 15-11-18.
 */
public class TimingFilter implements Filter {
    protected FilterConfig config;
    private static Log _log = LogFactory.getLog(TimingFilter.class);
    private static DecimalFormat format = new DecimalFormat();

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        long startTime = System.nanoTime();
        filterChain.doFilter(request, response);
        long elapsed = System.nanoTime()-startTime;;
        String name = "servlet";
        if (request instanceof HttpServletRequest) {
            name = ((HttpServletRequest) request).getRequestURI();
        }
        //config.getServletContext().log(name + " took " + elapsed + " ns..." + format.format((elapsed/1000000d))+" ms"));
        _log.info(name + " took " + elapsed + " ns..." + format.format((elapsed/1000000d))+" ms");
    }

    public void destroy() {

    }
}
