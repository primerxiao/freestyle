package com.primerxiao.freestyle.common.dll;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface PrimerDll extends Library {
    PrimerDll instance = (PrimerDll) Native.load("D:\\IdeaProjects\\primer-dll\\cmake-build-debug-mingw-64\\libprimer_dll.dll", PrimerDll.class);
    void hello();
}
