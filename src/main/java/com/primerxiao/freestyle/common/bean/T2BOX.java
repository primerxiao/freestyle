package com.primerxiao.freestyle.common.bean;

import com.primerxiao.freestyle.common.constant.InstanceConstant;
import com.primerxiao.freestyle.common.util.AppUtils;
import com.sun.jna.platform.win32.WinDef;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class T2BOX {
    /**
     * exe执行文件路径
     */
    private String exeFilePath;
    /**
     * 窗口标题
     */
    private String wTitle;
    /**
     * 窗口句柄
     */
    private WinDef.HWND hwnd;

    /**
     * 如果t2box没启动那么就启动 如果已经启动那么就封装信息
     * @param exeFilePath
     * @param wTitle
     */

    public T2BOX(String exeFilePath,String wTitle) {
        try {
            this.exeFilePath = exeFilePath;
            this.wTitle=wTitle;
            //获取窗口句柄
            WinDef.HWND hwnd = InstanceConstant.user32.FindWindow(null, wTitle);
            if (hwnd == null) {
                //程序没启动 启动程序
                AppUtils.openFile(exeFilePath);
                Thread.sleep(2000);
                //刷新窗口句柄
                this.hwnd = InstanceConstant.user32.FindWindow(null, wTitle);
            } else {
                this.hwnd=hwnd;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
