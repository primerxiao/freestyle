package com.primerxiao.freestyle.config;

import com.jacob.activeX.ActiveXComponent;
import com.primerxiao.freestyle.common.dll.DmRegDll;
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
        instance.SetDllPathA("E:\\Project\\JavaProject\\freestyle\\src\\main\\resources\\dll\\dm.dll", 0L);
        return new ActiveXComponent("dm.dmsoft");
    }
}
