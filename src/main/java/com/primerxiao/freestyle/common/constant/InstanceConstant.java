package com.primerxiao.freestyle.common.constant;

import com.sun.jna.platform.win32.GDI32;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;

public class InstanceConstant {
    public static final User32 user32= User32.INSTANCE;
    public static final Kernel32 kernel32= Kernel32.INSTANCE;
    public static final GDI32 gdi32= GDI32.INSTANCE;
}
