package com.primerxiao.freestyle;

import com.primerxiao.freestyle.common.constant.AppConstant;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class FreestyleApplication implements ApplicationContextAware {

    public static ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(FreestyleApplication.class, args);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        FreestyleApplication.applicationContext=applicationContext;
    }

}
