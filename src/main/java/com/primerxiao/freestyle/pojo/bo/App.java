package com.primerxiao.freestyle.pojo.bo;

import com.primerxiao.freestyle.common.constant.InstanceConstant;
import com.primerxiao.freestyle.common.util.AppUtils;
import com.sun.jna.platform.win32.WinDef;

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
    }

    public void close(){
        
    }

    public String getExeFilePath() {
        return exeFilePath;
    }

    public void setExeFilePath(String exeFilePath) {
        this.exeFilePath = exeFilePath;
    }

    public String getwTitle() {
        return wTitle;
    }

    public void setwTitle(String wTitle) {
        this.wTitle = wTitle;
    }

    public WinDef.HWND getHwnd() {
        return hwnd;
    }

    public void setHwnd(WinDef.HWND hwnd) {
        this.hwnd = hwnd;
    }

    public WinDef.POINT getLeftUpPoint() {
        return leftUpPoint;
    }

    public void setLeftUpPoint(WinDef.POINT leftUpPoint) {
        this.leftUpPoint = leftUpPoint;
    }

    public WinDef.POINT getRightDownPoint() {
        return rightDownPoint;
    }

    public void setRightDownPoint(WinDef.POINT rightDownPoint) {
        this.rightDownPoint = rightDownPoint;
    }

    public WinDef.POINT getCusLeftUpPoint() {
        return cusLeftUpPoint;
    }

    public void setCusLeftUpPoint(WinDef.POINT cusLeftUpPoint) {
        this.cusLeftUpPoint = cusLeftUpPoint;
    }

    public WinDef.POINT getCusRightDownPoint() {
        return cusRightDownPoint;
    }

    public void setCusRightDownPoint(WinDef.POINT cusRightDownPoint) {
        this.cusRightDownPoint = cusRightDownPoint;
    }
}
