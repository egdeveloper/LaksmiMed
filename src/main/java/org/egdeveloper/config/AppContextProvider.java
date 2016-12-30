package org.egdeveloper.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Developer: egdeveloper
 * Creation-Date: 10.08.16
 */

@Component
public class AppContextProvider implements ApplicationContextAware{

    private static ApplicationContext applicationContext_ = null;

    public static ApplicationContext getApplicationContext() {
        return applicationContext_;
    }
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationContext_ = applicationContext;
    }
}
