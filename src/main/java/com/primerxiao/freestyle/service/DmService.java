package com.primerxiao.freestyle.service;

import com.sun.istack.internal.Nullable;
import com.sun.jna.platform.win32.WinDef;

public interface DmService {

    /**
     * 注册大漠插件
     * @param regCode 注册码
     * @param verInfo 版本信息
     * @return boolean
     */
    boolean reg(String regCode, @Nullable String verInfo);

    /**
     * 设置字库
     * @param index 索引
     * @param filePath 文件路径
     * @return boolean
     */
    boolean setDict(int index, String filePath);

    /**
     * 获取窗口标题
     * @param hwnd 句柄
     * @return 窗口title
     */
    String getWindowTitle(WinDef.HWND hwnd);

    /**
     * 获取窗口在屏幕上的位置
     * @param hwnd 窗口句柄
     * @return
     */
    WinDef.POINT[] getWindowRect(WinDef.HWND hwnd);

    /**
     * 获取窗口客户区的位置
     * @param hwnd 窗口句柄
     * @return
     */
    WinDef.POINT[] getClientRect(WinDef.HWND hwnd);

    boolean moveWindow(WinDef.HWND hwnd, int x, int y);

    /**
     * 查找字符串
     * @param pointA 区域左上坐标
     * @param pointB 区域右下坐标
     * @param str 要查找的字符串
     * @param colorFormat 颜色格式串
     * @param sim 相似度
     * @return
     */
    WinDef.POINT findStrFast(WinDef.POINT pointA, WinDef.POINT pointB, String str, String colorFormat, float sim);

    boolean bindWindow(WinDef.HWND hwnd, String display, String mouse, String keypad, int mode);

    boolean bindWindowEx(WinDef.HWND hwnd, String display, String mouse, String keypad, String pub, int mode);

    boolean unBindWindow();

    boolean moveTo(WinDef.POINT point);

    boolean leftClick();

    boolean leftDoubleClick();

}
