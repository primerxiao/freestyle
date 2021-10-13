package com.primerxiao.freestyle;

import com.github.jonatino.misc.MemoryBuffer;
import com.github.jonatino.process.Module;
import com.github.jonatino.process.Process;
import com.github.jonatino.process.Processes;
import com.primerxiao.freestyle.common.constant.AppConstant;
import com.primerxiao.freestyle.common.constant.InstanceConstant;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.ptr.IntByReference;
import org.junit.Test;

public class 操作内存Test {
    @Test
    public void 读取内存() {
        //获取窗口句柄
        WinDef.HWND hwnd = InstanceConstant.user32.FindWindow(null, AppConstant.T2BOX_TITLE);
        IntByReference i = new IntByReference();
        InstanceConstant.user32.GetWindowThreadProcessId(hwnd, i);
        Process process = Processes.byId(i.getValue());
        MemoryBuffer read = process.read(0x01552c2c, 4);
        System.out.println(read.getInt());
    }

    @Test
    public void 修改内存() {
        //获取窗口句柄
        WinDef.HWND hwnd = InstanceConstant.user32.FindWindow(null, AppConstant.T2BOX_TITLE);
        IntByReference i = new IntByReference();
        InstanceConstant.user32.GetWindowThreadProcessId(hwnd, i);
        Process process = Processes.byId(i.getValue());
        System.out.println(process);
        Process process1 = process.writeInt(0x01552c2c, 0);
        System.out.println(process1);
    }

    @Test
    public void 修改内存根据基址() {
        //获取窗口句柄
        WinDef.HWND hwnd = InstanceConstant.user32.FindWindow(null, AppConstant.T2BOX_TITLE);
        IntByReference i = new IntByReference();
        InstanceConstant.user32.GetWindowThreadProcessId(hwnd, i);
        Process process = Processes.byId(i.getValue());
        //获取模块地址
        process.initModules();
        Module module = process.findModule("T2Box.exe");
        long address = module.address();
        long l = process.readLong(process.readLong(address+0x3285F0 )+0x12c8);
        System.out.println(Long.toHexString(l));
    }
}
