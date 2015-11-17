package com.mobiscloud.asyncmvc.struts;

import org.apache.struts.action.ActionServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by leeyg on 15-11-16.
 */
public class AsyncActionServlet extends ActionServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        super.doGet(request, response);
    }
}

