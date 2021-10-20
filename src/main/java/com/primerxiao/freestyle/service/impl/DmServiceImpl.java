package com.primerxiao.freestyle.service.impl;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Variant;
import com.primerxiao.freestyle.common.constant.DmMethodConstant;
import com.primerxiao.freestyle.service.DmService;
import com.sun.istack.internal.Nullable;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author primerxiao
 */
@Service
public class DmServiceImpl implements DmService {

    @Autowired
    private ActiveXComponent activeXComponent;

    @Override
    public boolean reg(String reg_code, @Nullable String ver_info) {
        Variant reg = activeXComponent.invoke(DmMethodConstant.REG, new Variant(reg_code), new Variant(ver_info));
        int anInt = reg.getInt();
        return anInt==1;
    }

    @Override
    public boolean setDict(int index, String file) {
        Variant setDict = activeXComponent.invoke(DmMethodConstant.SETDICT, new Variant(index), new Variant(file));
        if (setDict.getInt() == 1) {
            return true;
        }
        return false;
    }

    @Override
    public String getWindowTitle(WinDef.HWND hwnd) {
        long l = getNativaValue(hwnd);
        Variant invoke = activeXComponent.invoke(DmMethodConstant.GETWINDOWTITLE, String.valueOf(l));
        return invoke.getString();
    }

    @Override
    public WinDef.POINT[] getWindowRect(WinDef.HWND hwnd) {
        Variant variant = new Variant();
        variant.putLong(getNativaValue(hwnd));
        Variant vx1 = new Variant(0, true);
        Variant vx2 = new Variant(0, true);
        Variant vx3 = new Variant(0, true);
        Variant vx4 = new Variant(0, true);
        Variant invoke = activeXComponent.invoke(DmMethodConstant.GETWINDOWRECT, variant, vx1, vx2, vx3, vx4);
        if (invoke.getInt() != 1) {
            throw new RuntimeException("获取窗口坐标失败");
        }
        WinDef.POINT[] points = new WinDef.POINT[2];
        points[0] = new WinDef.POINT(vx1.getInt(), vx2.getInt());
        points[1] = new WinDef.POINT(vx3.getInt(), vx4.getInt());
        return points;
    }


    @Override
    public WinDef.POINT[] getClientRect(WinDef.HWND hwnd) {
        Variant variant = new Variant();
        variant.putLong(getNativaValue(hwnd));
        Variant vx1 = new Variant(0, true);
        Variant vx2 = new Variant(0, true);
        Variant vx3 = new Variant(0, true);
        Variant vx4 = new Variant(0, true);
        Variant invoke = activeXComponent.invoke(DmMethodConstant.GETCLIENTRECT, variant, vx1, vx2, vx3, vx4);
        int anInt = invoke.getInt();
        if (anInt != 1) {
            throw new RuntimeException("获取窗口坐标失败");
        }
        WinDef.POINT[] points = new WinDef.POINT[2];
        points[0] = new WinDef.POINT(vx1.getInt(), vx2.getInt());
        points[1] = new WinDef.POINT(vx3.getInt(), vx4.getInt());
        return points;
    }

    @Override
    public boolean moveWindow(WinDef.HWND hwnd, int x, int y) {
        Variant moveWindow = activeXComponent.invoke(DmMethodConstant.MOVEWINDOW, new Variant(getNativaValue(hwnd)), new Variant(x), new Variant(y));
        int anInt = moveWindow.getInt();
        if (anInt == 1) {
            return true;
        }
        return false;
    }

    @Override
    public WinDef.POINT findStrFast(WinDef.POINT pointA, WinDef.POINT pointB, String str, String colorFormat, float sim) {
        Variant variantX = new Variant(-1,true);
        Variant variantY = new Variant(-1,true);
         activeXComponent.invoke(DmMethodConstant.FINDSTRFAST,
                new Variant(pointA.x),
                new Variant(pointA.y),
                new Variant(pointB.x),
                new Variant(pointB.y),
                new Variant(str),
                new Variant(colorFormat),
                new Variant(sim),
                variantX,
                variantY
        );
        if (variantX.getInt() >= 0 && variantY.getInt() >= 0) {
            return new WinDef.POINT(variantX.getInt(), variantY.getInt());
        }
        return null;
    }

    @Override
    public boolean bindWindow(WinDef.HWND hwnd, String display, String mouse, String keypad, int mode) {
        Variant bindWindowEx = activeXComponent.invoke(DmMethodConstant.BINDWINDOW,
                new Variant(getNativaValue(hwnd)),
                new Variant(display),
                new Variant(mouse),
                new Variant(keypad),
                new Variant(mode)
        );
        return bindWindowEx.getInt() == 1;
    }

    @Override
    public boolean bindWindowEx(WinDef.HWND hwnd, String display, String mouse, String keypad, String pub, int mode) {
        Variant bindWindowEx = activeXComponent.invoke(DmMethodConstant.BINDWINDOWEX,
                new Variant(getNativaValue(hwnd)),
                new Variant(display),
                new Variant(mouse),
                new Variant(keypad),
                new Variant(pub),
                new Variant(mode)
        );
        return bindWindowEx.getInt() == 1;
    }

    @Override
    public boolean unBindWindow() {
        int unBindWindow = activeXComponent.invoke(DmMethodConstant.UNBINDWINDOW).getInt();
        return unBindWindow == 1;
    }

    @Override
    public boolean moveTo(WinDef.POINT point) {
        int moveTo = activeXComponent.invoke(DmMethodConstant.MOVETO, new Variant(point.x), new Variant(point.y)).getInt();
        return moveTo == 1;
    }

    @Override
    public boolean leftClick() {
        Variant leftClick = activeXComponent.invoke(DmMethodConstant.LEFTCLICK);
        return leftClick.getInt() == 1;
    }

    private <T> T excuteNoVariaFun(String methodName,Class<T> tClass){
        Variant invoke = activeXComponent.invoke(methodName);
        //if (tClass instanceof int.class)
        return null;
    }


    private long getNativaValue(WinDef.HWND hwnd) {
        Pointer pointer = hwnd.getPointer();
        return Pointer.nativeValue(pointer);
    }

}
