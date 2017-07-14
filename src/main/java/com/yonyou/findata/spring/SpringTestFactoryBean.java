package com.yonyou.findata.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author: pizhihui
 * @datae: 2017-05-26
 */
public class SpringTestFactoryBean implements FactoryBean, ApplicationContextAware {

    private ApplicationContext context;

    public Object getObject() throws Exception {
        System.out.println("getObject.................");
        String name = context.getClassLoader().getClass().getName();
        System.out.println("getObject................." + name);
        return null;
    }

    public Class<?> getObjectType() {
        return SpringTestFactoryBean.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
