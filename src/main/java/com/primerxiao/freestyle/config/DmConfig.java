package com.primerxiao.freestyle.config;

import com.jacob.activeX.ActiveXComponent;
import com.primerxiao.freestyle.common.dll.DmRegDll;
import com.primerxiao.freestyle.service.DmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 大漠配置插件
 */
@Configuration
public class DmConfig {

    @Bean
    public ActiveXComponent activeXComponent(){
        DmRegDll instance = DmRegDll.INSTANCE;
        instance.SetDllPathA("D:\\IdeaProjects\\freestyle\\src\\main\\resources\\dll\\dm.dll", 0L);
        return new ActiveXComponent("dm.dmsoft");
    }
}
