package com.primerxiao.freestyle.task;

import com.primerxiao.freestyle.common.constant.AppConstant;
import com.primerxiao.freestyle.common.constant.InstanceConstant;
import com.primerxiao.freestyle.service.FreeStyleService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log
@Component
public class FreeStyleTask {

    @Autowired
    private FreeStyleService freeStyleService;


    /**
     * 主流程定时器
     *  获取当前的场景和状态
     *  根据获取到的场景和状态进行相应的处理
     */
    @Scheduled(fixedDelay = 1000 * 1)
    public void freeStyleMainProcess() {

    }
    /*@Scheduled(fixedDelay = 5000 * 1)*/
    public void test() throws Exception {
        if (InstanceConstant.user32.FindWindow(null, AppConstant.T2BOX_TITLE) != null) {
            return;
        } else {
           freeStyleService.startFreeStyle(AppConstant.T2BOX_PATH, AppConstant.T2BOX_TITLE);
        }
    }
}
