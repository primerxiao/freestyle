package com.primerxiao.freestyle;

import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class FreestyleApplication implements ApplicationContextAware {

    public static ApplicationContext applicationContext;



    

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        FreestyleApplication.applicationContext=applicationContext;
    }

}
