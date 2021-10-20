package com.primerxiao.freestyle;

import com.primerxiao.freestyle.pojo.bo.NotePadd;
import com.primerxiao.freestyle.service.DmService;
import com.sun.jna.platform.win32.WinDef;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FreestyleApplication.class)
public class TxtAppTest {

    @Autowired
    private DmService dmService;

    public NotePadd t2BOX;

    @Before
    public void before() throws Exception {
        t2BOX = new NotePadd(NotePadd.wTitle, NotePadd.wTitle);
        t2BOX.start();
        boolean b = dmService.setDict(0, "D:\\IdeaProjects\\freestyle\\src\\main\\resources\\dmzk.txt");
        System.out.println("设置字库状态：" + b);
    }

    @Test
    public void startTest() throws Exception {
        //dmService.getWindowTitle(t2BOX.getHwnd());
        WinDef.POINT[] points = dmService.getWindowRect(t2BOX.getHwnd());
        System.out.println(points[0].x);
        System.out.println(points[0].y);
        WinDef.POINT[] point1s = dmService.getClientRect(t2BOX.getHwnd());
        System.out.println(point1s[0].x);
        System.out.println(point1s[1].y);
    }

    @Test
    public void moveWindowTest(){
        boolean b = dmService.moveWindow(t2BOX.getHwnd(), 0, 0);
        System.out.println(b);
    }

    @Test
    public void findStrTest(){
        boolean b1 = dmService.bindWindow(t2BOX.getHwnd(),
                "dx2", "windows", "windows", 0);
        System.out.println(b1);
    }
}
