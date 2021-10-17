package com.primerxiao.freestyle.pojo.bo;

import com.primerxiao.freestyle.common.constant.InstanceConstant;
import com.primerxiao.freestyle.common.util.AppUtils;
import com.sun.jna.platform.win32.WinDef;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class T2BOX extends App {

    /**
     * 如果t2box没启动那么就启动 如果已经启动那么就封装信息
     * @param exeFilePath
     * @param wTitle
     */
    public T2BOX(String exeFilePath,String wTitle) {
       this.exeFilePath=exeFilePath;
       this.wTitle=wTitle;
    }
}
