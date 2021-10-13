package com.primerxiao.freestyle.service.impl;

import com.primerxiao.freestyle.common.constant.AppConstant;
import com.primerxiao.freestyle.common.constant.InstanceConstant;
import com.primerxiao.freestyle.service.FreeStyleService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FreeStyleServiceImpl implements FreeStyleService {


    @Override
    public boolean startFreeStyle(String filePath, String wTitle) throws Exception {
        if (InstanceConstant.user32.FindWindow(null, AppConstant.T2BOX_TITLE) != null) {
            return false;
        }
        //打开程序
        Process exec = Runtime.getRuntime().exec(
                "cmd /c "
                        + FilenameUtils.getPrefix(filePath).replaceAll("/", "")
                        + " & "
                        + "cd " + FilenameUtils.getFullPath(filePath) + " & "
                        + FilenameUtils.getName(AppConstant.T2BOX_PATH)
        );
        if (Objects.isNull(exec)) {
            return false;
        }
        return true;
    }

}
