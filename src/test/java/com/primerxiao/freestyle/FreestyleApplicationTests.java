package com.primerxiao.freestyle;

import com.primerxiao.freestyle.pojo.bo.T2BOX;
import com.primerxiao.freestyle.common.constant.AppConstant;
import com.primerxiao.freestyle.common.constant.InstanceConstant;
import com.primerxiao.freestyle.common.constant.MessageConstant;
import com.primerxiao.freestyle.common.dll.FreeStyleDll;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.*;
import com.sun.jna.ptr.IntByReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FreestyleApplicationTests {

    @Test
    public void 遍历任务管理器进程() throws Exception {
        for (Map.Entry<Integer, String> integerStringEntry : getTaskPID().entrySet()) {
            System.out.println(integerStringEntry.getKey() + "||||||||||||" + integerStringEntry.getValue());
        }
    }

    public static Map<Integer, String> getTaskPID() {
        User32 user32 = User32.INSTANCE;
        Map<Integer, String> map = new HashMap<>();
        IntByReference i = new IntByReference();//放PID
        user32.EnumWindows(new User32.WNDENUMPROC() {
            @Override
            public boolean callback(WinDef.HWND h, Pointer p) {
                char[] buffer = new char[1024 * 2];
                user32.GetWindowThreadProcessId(h, i);
                user32.GetWindowText(h, buffer, 1024);
                if (user32.IsWindow(h) && user32.IsWindowEnabled(h) && user32.IsWindowVisible(h)) {
                    map.put(i.getValue(), Native.toString(buffer));
                }
                return true;
            }
        }, null);
        //获得到的窗口PID集合
        return map;
    }

    @Test
    public void 打开程序() throws InterruptedException {
        T2BOX t2BOX = new T2BOX(AppConstant.T2BOX_PATH, AppConstant.FREESTYLE_TITLE);
        System.out.println(t2BOX.toString());
        WinDef.HWND hwnd = InstanceConstant.user32.FindWindow(null, AppConstant.FREESTYLE_TITLE);
        WinDef.RECT rect=new WinDef.RECT();
        InstanceConstant.user32.GetWindowRect(hwnd, rect);
        System.out.println("width:"+(rect.right - rect.left));
        System.out.println("heigth:"+(rect.bottom-rect.top));
        //InstanceConstant.user32.MoveWindow(hwnd, 0, 0, rect.right - rect.left, rect.bottom-rect.top, false);
        //InstanceConstant.user32.SetForegroundWindow(hwnd);
        InstanceConstant.user32.GetWindowRect(hwnd, rect);
        System.out.println(rect.left);
        System.out.println(rect.right);
        System.out.println(rect.top);
        System.out.println(rect.bottom);
        // InstanceConstant.user32.SetCursorPos(1050, 615);
        //点击登录533 459
        WinDef.RECT cRect=new WinDef.RECT();
        InstanceConstant.user32.GetClientRect(hwnd, cRect);
        System.out.println(cRect.left);
        System.out.println(cRect.right);
        System.out.println(cRect.top);
        System.out.println(cRect.bottom);

        WinDef.WPARAM wparam = new WinDef.WPARAM();
        WinDef.LPARAM lparam = new WinDef.LPARAM();
        wparam.setValue(0x00000001);
        lparam.setValue(0x01ce0211);
        //Pointer pointer = lparam.toPointer();
        InstanceConstant.user32.PostMessage(hwnd, MessageConstant.WM_LBUTTONDOWN,wparam, lparam);
        InstanceConstant.user32.PostMessage(hwnd, MessageConstant.WM_LBUTTONUP,wparam, lparam);
    }
    @Test
    public void 获取进程路径() throws InterruptedException {

        WinDef.HWND hwnd = InstanceConstant.user32.FindWindow(null, AppConstant.T2BOX_TITLE);
        WinDef.RECT rect=new WinDef.RECT();
        IntByReference i = new IntByReference();
        InstanceConstant.user32.GetWindowThreadProcessId(hwnd, i);
        WinNT.HANDLE gHandle = InstanceConstant.kernel32.OpenProcess(WinNT.PROCESS_ALL_ACCESS, false, i.getValue());
        //System.out.println(FreeStyleDll.instance.ListProcessModuleBaseAddr(i.getValue(),AppConstant.T2BOX_MOUDLE_NAME));


        WinDef.DWORD dword = new WinDef.DWORD();
        dword.setValue(i.getValue());
        WinNT.HANDLE handle = InstanceConstant.kernel32.CreateToolhelp32Snapshot(Tlhelp32.TH32CS_SNAPMODULE, dword);

        Tlhelp32.MODULEENTRY32W moduleentry32W = new Tlhelp32.MODULEENTRY32W();

        //Kernel32Util.closeHandle(handle);
        boolean b = InstanceConstant.kernel32.Module32FirstW(handle, moduleentry32W);
        System.out.println(b);
        System.out.println(moduleentry32W.szModule);
        Pointer modBaseAddr = moduleentry32W.modBaseAddr;
        char[] chars = new char[32];
        WinDef.HWND hwnd1 = new WinDef.HWND();
        hwnd1.setPointer(Pointer.createConstant(0x0003096E));
        int i1 = InstanceConstant.user32.GetWindowText(hwnd1, chars, 32);
        System.out.println(hwnd1);

        //System.out.println(modBaseAddr.getInt(0x3285F0));

    }

    @Test
    public void 发送消息点击() {
        //获取窗口句柄
        WinDef.HWND hwnd = InstanceConstant.user32.FindWindow(null, AppConstant.T2BOX_TITLE);
        //该函数返回创建指定窗口线程的标识和创建窗口的进程的标识符
        IntByReference i = new IntByReference();
        InstanceConstant.user32.GetWindowThreadProcessId(hwnd, i);
        System.out.println(i.getValue());
        //打开进程对象，并返回进程的句柄
        WinNT.HANDLE handle = InstanceConstant.kernel32.OpenProcess(WinNT.PROCESS_VM_READ, false, i.getValue());
        System.out.println(handle);
        //打开进程
        //读取开启窗口化的状态 0 取消 1 勾选
        IntByReference intByReference = new IntByReference();
        intByReference.setValue(4);
        long tt = 0x76961c;
        Pointer aNull = Memory.NULL;
        //boolean b = kernel32.WriteProcessMemory(process, address, toWrite, size, null);
        BaseTSD.SIZE_T size_t = new BaseTSD.SIZE_T(4);
        int i1 = FreeStyleDll.instance.readIntMemoryValue(handle, i.getValue(), tt, size_t);
        System.out.println(i1);
        String hex = "0076961c";
        int i2 = Integer.parseInt(hex, 16);
        System.out.println(i2);
        System.out.println(Long.toHexString(tt));

    }

    @Test
    public void 点击开始按钮() {

        WinDef.HWND hwnd = new WinDef.HWND();
        hwnd.setPointer(Pointer.createConstant(0x00030922));
        WinDef.WPARAM wparam = new WinDef.WPARAM();
        WinDef.LPARAM lparam = new WinDef.LPARAM();
        wparam.setValue(0);
        lparam.setValue(0);
        InstanceConstant.user32.SendMessage(hwnd, MessageConstant.WM_GETDLGCODE, wparam, lparam);
        WinDef.RECT rect = new WinDef.RECT();

        InstanceConstant.user32.GetWindowRect(hwnd, rect);
        System.out.println(rect.left);
        System.out.println(rect.right);
        System.out.println(rect.top);
        System.out.println(rect.bottom);

    }

    @Test
    public void 后台程序窗口截图() throws IOException {
        //获取窗口句柄
        WinDef.HWND hwnd = InstanceConstant.user32.FindWindow(null, AppConstant.T2BOX_TITLE);
        BufferedImage screenshot = GDI32Util.getScreenshot(hwnd);
        ImageIO.write(screenshot, "bmp", new File("D:\\test.bmp"));
    }

}
