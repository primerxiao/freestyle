package com.primerxiao.freestyle;

import com.github.jonatino.process.Process;
import com.github.jonatino.process.Processes;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.ptr.IntByReference;
import org.junit.Test;

import static com.primerxiao.freestyle.common.constant.InstanceConstant.user32;

public class 测试 {
    @Test
    public void name() {

        WinDef.HWND t2BOX = user32.FindWindow(null, "T2BOX");
        IntByReference intByReference = new IntByReference();
        int i = user32.GetWindowThreadProcessId(t2BOX, intByReference);
        //地址 ox0074EA24
        Process process = Processes.byId(intByReference.getValue());
        Process process1 = process.writeInt(0x0074EA24, 0);


    }
}
