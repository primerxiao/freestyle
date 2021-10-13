package com.primerxiao.freestyle;

import com.sun.jna.platform.win32.BaseTSD;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.*;
import java.awt.event.KeyEvent;

import static com.primerxiao.freestyle.common.constant.AppConstant.FREESTYLE_TITLE;
import static com.primerxiao.freestyle.common.constant.InstanceConstant.user32;

@RunWith(SpringRunner.class)
@SpringBootTest
public class 键盘输入Test {

    @Test
    public void Robot方式() throws AWTException {
        System.setProperty("java.awt.headless", "false");
        //获取窗口句柄
        WinDef.HWND hwnd = user32.FindWindow(null, FREESTYLE_TITLE);
        //发送键盘输入
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_S);
        robot.keyRelease(KeyEvent.VK_S);
        robot.keyPress(KeyEvent.VK_S);
        robot.keyRelease(KeyEvent.VK_S);
        robot.keyPress(KeyEvent.VK_S);
        robot.keyRelease(KeyEvent.VK_S);

    }

    @Test
    public void SendInput方式() {

        //WinDef.HWND hwnd = user32.FindWindow(null, FREESTYLE_TITLE);
        // Prepare input reference
        WinUser.INPUT input = new WinUser.INPUT(  );
        input.type = new WinDef.DWORD( WinUser.INPUT.INPUT_KEYBOARD );
        input.input.setType("ki"); // Because setting INPUT_INPUT_KEYBOARD is not enough: https://groups.google.com/d/msg/jna-users/NDBGwC1VZbU/cjYCQ1CjBwAJ
        input.input.ki.wScan = new WinDef.WORD( 0 );
        input.input.ki.time = new WinDef.DWORD( 0 );
        input.input.ki.dwExtraInfo = new BaseTSD.ULONG_PTR( 0 );
        // Press "a"
        input.input.ki.wVk = new WinDef.WORD( 'A' ); // 0x41
        input.input.ki.dwFlags = new WinDef.DWORD( 0 );  // keydown
        User32.INSTANCE.SendInput( new WinDef.DWORD( 1 ), ( WinUser.INPUT[] ) input.toArray( 1 ), input.size() );
        // Release "a"
        input.input.ki.wVk = new WinDef.WORD( 'A' ); // 0x41
        input.input.ki.dwFlags = new WinDef.DWORD( 2 );  // keyup
        User32.INSTANCE.SendInput( new WinDef.DWORD( 1 ), ( WinUser.INPUT[] ) input.toArray( 1 ), input.size() );
    }
}
