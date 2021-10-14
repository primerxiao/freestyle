package com.primerxiao.freestyle.common.dll;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * @author primerxiao
 */
public interface DmRegDll extends Library {

    DmRegDll INSTANCE = (DmRegDll) Native.load("DmReg.dll", DmRegDll.class);

    void SetDllPathA(String path,Long mode);
}
