package com.primerxiao.freestyle.pojo.bo;

import com.primerxiao.freestyle.common.constant.InstanceConstant;
import com.primerxiao.freestyle.common.util.AppUtils;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef;
import lombok.Data;

@Data
public abstract class App {
    /**
     * exe执行文件路径
     */
    public String exeFilePath;
    /**
     * 窗口标题
     */
    public String wTitle;
    /**
     * 窗口句柄 jna
     */
    public WinDef.HWND hwnd;

    public long hwndNativValue;

    /**
     * app左上角屏幕坐标
     */
    public WinDef.POINT leftUpPoint;

    /**
     * app右下角屏幕坐标
     */
    public WinDef.POINT rightDownPoint;

    /**
     * 客户区左上角屏幕坐标
     */
    public WinDef.POINT cusLeftUpPoint;

    /**
     * 客户区右下角屏幕坐标
     */
    public WinDef.POINT cusRightDownPoint;

    public App(String exeFilePath,String wTitle) {
        this.exeFilePath=exeFilePath;
        this.wTitle=wTitle;
    }

    public void start() throws Exception {
        //获取窗口句柄
        WinDef.HWND hwnd = InstanceConstant.user32.FindWindow(null, wTitle);
        if (hwnd == null) {
            //程序没启动 启动程序
            AppUtils.openFile(exeFilePath);
            Thread.sleep(2000);
            //刷新窗口句柄
            this.hwnd = InstanceConstant.user32.FindWindow(null, wTitle);
        } else {
            this.hwnd = hwnd;
        }
        assert hwnd != null;
        this.hwndNativValue = Pointer.nativeValue(hwnd.getPointer());
    }

    public void close(){
        
    }

}
