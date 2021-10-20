package com.primerxiao.freestyle.config;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Variant;
import com.primerxiao.freestyle.common.constant.DmMethodConstant;
import com.primerxiao.freestyle.common.dll.DmRegDll;
import com.primerxiao.freestyle.service.DmService;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

/**
 * 大漠配置插件
 *
 * @author primerxiao
 */
@Configuration
@Log
public class DmConfig {

    @Bean
    public ActiveXComponent activexComponent() throws Exception {
        try {
            final ClassPathResource classPathResource = new ClassPathResource("dm.dll");
            final ClassPathResource dmzkClassPathResource = new ClassPathResource("dmzk.txt");
            final File dmDllFile = classPathResource.getFile();
            final File dmzkFile = dmzkClassPathResource.getFile();
            //免注册调用大漠
            DmRegDll instance = DmRegDll.INSTANCE;
            instance.SetDllPathA(dmDllFile.getAbsolutePath(), 0L);
            ActiveXComponent activexComponent = new ActiveXComponent("dm.dmsoft");
            //注册大漠
            Variant reg = activexComponent.invoke(
                    DmMethodConstant.REG,
                    new Variant("rbdo9632038d7480c7853427c504ee9bb705c6d"),
                    new Variant(""));
            if (reg.getInt() == 1) {
                log.info("注册成功");
            }
            //设置字库
            Variant setDict = activexComponent.invoke(DmMethodConstant.SETDICT, new Variant(0), new Variant(dmzkFile.getAbsolutePath()));
            if (setDict.getInt() == 1) {
                log.info("设置字库成功");
            }
            return activexComponent;
        } catch (Exception e) {
            log.info("配置失败，异常信息{}" + e.getMessage());
            throw e;
        }
    }
}
