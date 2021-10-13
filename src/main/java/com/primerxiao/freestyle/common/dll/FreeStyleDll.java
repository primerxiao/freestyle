package com.primerxiao.freestyle.common.dll;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.BaseTSD;
import com.sun.jna.platform.win32.WinNT;

public interface FreeStyleDll extends Library {
    FreeStyleDll instance = (FreeStyleDll) Native.loadLibrary("D:\\IdeaProjects\\freestyle\\src\\main\\resources\\dll\\FreeStyleDll.dll", FreeStyleDll.class);
    boolean setMemoryValue(String pTitle,int memoryAddr,int value);
    int getMemoryValue(String pTitle,int memoryAddr);
    int readIntMemoryValue(WinNT.HANDLE Hprocess, int pId, long memoryAddr, BaseTSD.SIZE_T nSize);
    int ListProcessModuleBaseAddr( int dwPID,String moduleName );
    boolean ListProcessModules( int dwPID );
}
