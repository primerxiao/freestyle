package com.primerxiao.freestyle.common.util;

import com.primerxiao.freestyle.common.constant.AppConstant;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import org.apache.commons.io.FilenameUtils;

import java.util.Objects;

import static com.primerxiao.freestyle.common.constant.InstanceConstant.kernel32;
import static com.primerxiao.freestyle.common.constant.InstanceConstant.user32;
import static com.sun.jna.platform.win32.WinNT.PROCESS_ALL_ACCESS;

public class AppUtils {
    /**
     * 根据可执行程序路径打开程序
     * @param filePath 程序可执行路径
     * @throws Exception
     */
    public static void openFile(String filePath) throws Exception {
        //打开程序
        Process exec = Runtime.getRuntime().exec(
                "cmd /c "
                        + FilenameUtils.getPrefix(filePath).replaceAll("/", "")
                        + " & "
                        + "cd " + FilenameUtils.getFullPath(filePath) + " & "
                        + FilenameUtils.getName(AppConstant.T2BOX_PATH)
        );
    }
    /**
     * 根据窗口标题杀死进程
     * @param wTitle 窗口标题
     */
    public static void closeProcess(String wTitle) {
        WinDef.HWND hwnd = user32.FindWindow(null,wTitle);
        if (!Objects.isNull(hwnd)) {
            IntByReference processId = new IntByReference();
            user32.GetWindowThreadProcessId(hwnd, processId);
            WinNT.HANDLE handle = kernel32.OpenProcess(PROCESS_ALL_ACCESS, false, processId.getValue());
            IntByReference exitCode = new IntByReference();
            kernel32.GetExitCodeProcess(hwnd, exitCode);
            kernel32.TerminateProcess(handle, exitCode.getValue());
            if (!Objects.isNull(handle)) {
                kernel32.CloseHandle(handle);
            }
            if (!Objects.isNull(hwnd)) {
                kernel32.CloseHandle(hwnd);
            }
        }
    }
}
