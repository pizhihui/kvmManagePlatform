package com.yonyou.findata.Listener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author: pizhihui
 * @datae: 2017-05-26
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {


    public void contextInitialized(ServletContextEvent sce) {
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
        System.out.println("contextInitilalid......." + ctx.getApplicationName());
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed........");
    }
}
