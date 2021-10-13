package com.primerxiao.freestyle.service;

import java.io.IOException;

public interface FreeStyleService {
    /**
     * 根据指定可执行程序路径打开启动程序
     * @param filePath 程序绝对路径，包含后缀
     * @param wTitle 窗口标题，用于判断当前窗口是否打开
     * @return
     */
    boolean startFreeStyle(String filePath,String wTitle) throws IOException, InterruptedException, Exception;
}
