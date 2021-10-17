package com.primerxiao.freestyle.service.impl;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Variant;
import com.primerxiao.freestyle.service.DmService;
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
    public boolean SetDict(int index, String file) {
        Variant setDict = activeXComponent.invoke("SetDict", new Variant(index), new Variant(file));
        if (setDict.getInt() == 1) {
            return true;
        }
        return false;
    }

    @Override
    public String GetWindowTitle(WinDef.HWND hwnd) {
        long l = getNativaValue(hwnd);
        Variant invoke = activeXComponent.invoke("GetWindowTitle", String.valueOf(l));
        return invoke.getString();
    }

    @Override
    public WinDef.POINT[] GetWindowRect(WinDef.HWND hwnd) {
        long l = getNativaValue(hwnd);
        Variant variant = new Variant();
        variant.putLong(l);
        Variant vx1 = new Variant(0, true);
        Variant vx2 = new Variant(0, true);
        Variant vx3 = new Variant(0, true);
        Variant vx4 = new Variant(0, true);
        Variant invoke = activeXComponent.invoke("GetWindowRect", variant, vx1, vx2, vx3, vx4);
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
    public WinDef.POINT[] GetClientRect(WinDef.HWND hwnd) {
        long l = getNativaValue(hwnd);
        Variant variant = new Variant();
        variant.putLong(l);
        Variant vx1 = new Variant(0, true);
        Variant vx2 = new Variant(0, true);
        Variant vx3 = new Variant(0, true);
        Variant vx4 = new Variant(0, true);
        Variant invoke = activeXComponent.invoke("GetClientRect", variant, vx1, vx2, vx3, vx4);
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
    public boolean MoveWindow(WinDef.HWND hwnd, int x, int y) {
        long nativaValue = getNativaValue(hwnd);
        Variant moveWindow = activeXComponent.invoke("MoveWindow", new Variant(nativaValue), new Variant(x), new Variant(y));
        int anInt = moveWindow.getInt();
        if (anInt == 1) {
            return true;
        }
        return false;
    }

    @Override
    public WinDef.POINT FindStrFast(WinDef.POINT pointA, WinDef.POINT pointB, String str, String colorFormat, float sim) {
        Variant variantX = new Variant(-1);
        Variant variantY = new Variant(-1);
        Variant findStrFast = activeXComponent.invoke("FindStrFast",
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

    private long getNativaValue(WinDef.HWND hwnd) {
        Pointer pointer = hwnd.getPointer();
        return Pointer.nativeValue(pointer);
    }
}
