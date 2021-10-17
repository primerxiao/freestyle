package com.primerxiao.freestyle;


import com.jacob.activeX.ActiveXComponent;
import com.primerxiao.freestyle.common.dll.DmRegDll;
import com.primerxiao.freestyle.common.dll.PrimerDll;
import org.junit.Test;

import java.io.PushbackInputStream;

public class CallDllTest {
    @Test
    public void hello() {
        PrimerDll instance = PrimerDll.instance;
        instance.hello();
    }

    @Test
    public void dmTest() {
        DmRegDll instance = DmRegDll.INSTANCE;
        instance.SetDllPathA("E:\\Project\\JavaProject\\freestyle\\src\\main\\resources\\dll\\dm.dll", 0L);

        ActiveXComponent dm =new ActiveXComponent("dm.dmsoft");

        System.out.println(dm.invoke("Ver").getString());
    }
}
