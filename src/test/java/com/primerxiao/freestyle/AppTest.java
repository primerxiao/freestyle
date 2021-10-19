package com.primerxiao.freestyle;

import com.primerxiao.freestyle.common.constant.AppConstant;
import com.primerxiao.freestyle.pojo.bo.T2BOX;
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
public class AppTest {

    @Autowired
    private DmService dmService;

    public T2BOX t2BOX;

    @Before
    public void before() throws Exception {
        t2BOX = new T2BOX(AppConstant.T2BOX_PATH, AppConstant.T2BOX_TITLE);
        t2BOX.start();
        boolean b = dmService.SetDict(0, "E:\\Project\\JavaProject\\freestyle\\src\\main\\resources\\dmzk.txt");
        System.out.println("设置字库状态：" + b);
    }

    @Test
    public void startTest() throws Exception {
        //dmService.GetWindowTitle(t2BOX.getHwnd());
        WinDef.POINT[] points = dmService.GetWindowRect(t2BOX.getHwnd());
        System.out.println(points[0].x);
        System.out.println(points[0].y);
        WinDef.POINT[] point1s = dmService.GetClientRect(t2BOX.getHwnd());
        System.out.println(point1s[0].x);
        System.out.println(point1s[1].y);
    }

    @Test
    public void moveWindowTest(){
        boolean b = dmService.MoveWindow(t2BOX.getHwnd(), 0, 0);
        System.out.println(b);
    }

    @Test
    public void findStrTest(){
        boolean b1 = dmService.BindWindow(t2BOX.getHwnd(),
                "dx2", "windows", "windows", 0);
        System.out.println(b1);

        boolean b = dmService.BindWindowEx(
                t2BOX.getHwnd(),
                "dx2","windows","windows","",0
        );
        System.out.println(b);
        WinDef.POINT[] point1s = dmService.GetClientRect(t2BOX.getHwnd());
        WinDef.POINT findStrFast = dmService.FindStrFast(point1s[0], point1s[1], "开始游戏", "#0-000000", 1.0f);
        System.out.println(findStrFast.x + "  " + findStrFast.y);
    }
}
