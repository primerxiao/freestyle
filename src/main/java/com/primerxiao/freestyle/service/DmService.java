package com.primerxiao.freestyle.service;

import com.sun.istack.internal.Nullable;
import com.sun.jna.platform.win32.WinDef;

public interface DmService {

    int Reg(String reg_code, @Nullable String ver_info);

    boolean SetDict(int index, String file);

    /**
     * 获取窗口标题
     * @param hwnd 句柄
     * @return 窗口title
     */
    String GetWindowTitle(WinDef.HWND hwnd);

    /**
     * 获取窗口在屏幕上的位置
     * @param hwnd 窗口句柄
     * @return
     */
    WinDef.POINT[] GetWindowRect(WinDef.HWND hwnd);

    /**
     * 获取窗口客户区的位置
     * @param hwnd 窗口句柄
     * @return
     */
    WinDef.POINT[] GetClientRect(WinDef.HWND hwnd);

    boolean MoveWindow(WinDef.HWND hwnd, int x, int y);

    /**
     * 查找字符串
     * @param pointA 区域左上坐标
     * @param pointB 区域右下坐标
     * @param str 要查找的字符串
     * @param colorFormat 颜色格式串
     * @param sim 相似度
     * @return
     */
    WinDef.POINT FindStrFast(WinDef.POINT pointA, WinDef.POINT pointB, String str, String colorFormat, float sim);

    boolean BindWindow(WinDef.HWND hwnd, String display, String mouse, String keypad, int mode);

    boolean BindWindowEx(WinDef.HWND hwnd, String display, String mouse, String keypad, String pub, int mode);

    boolean UnBindWindow();

    boolean MoveTo(WinDef.POINT point);

    boolean LeftClick();

}
