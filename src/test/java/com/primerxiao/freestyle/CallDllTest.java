package com.primerxiao.freestyle;


import com.primerxiao.freestyle.common.dll.PrimerDll;
import org.junit.Test;

public class CallDllTest {
    @Test
    public void hello() {
        PrimerDll instance = PrimerDll.instance;
        instance.hello();
    }
}
