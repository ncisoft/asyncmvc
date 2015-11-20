package com.mobiscloud.asyncmvc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

/**
 * Uaed to intercept servlet and wrap to async call
 * Created by leeyg on 15-11-18.
 */
public class ASyncFilter implements Filter {
    protected FilterConfig config;
    private static Log _log = LogFactory.getLog(ASyncFilter.class);
    private static DecimalFormat format = new DecimalFormat();
    AsyncWorker asyncWorker;

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
        asyncWorker = new AsyncWorker();
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        asyncWorker.invokeServlet(new MethodWrapper<Boolean>(request, response, filterChain));
    }

    public void destroy() {

    }

    public class MethodWrapper<T> extends Method<T> {
        ServletRequest request;
        ServletResponse response;
        FilterChain filterChain;

        public MethodWrapper(ServletRequest request, ServletResponse response, FilterChain filterChain) {
            this.request = request;
            this.response = response;
            this.filterChain = filterChain;
        }

        public T call() throws Exception {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            _log.info("intercept " + httpServletRequest.getRequestURI());
            filterChain.doFilter(request, response);
            return null;
        }
    }
}
