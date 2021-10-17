package com.primerxiao.freestyle.entity;

import lombok.Data;

@Data
public class Button {
    /**
     * 主键id
     */
    private String ButtonId;
    /**
     * 按钮名称
     */
    private String ButtonName;
    /**
     * 按钮客户区x坐标
     */
    private int ButtonClientPosx;
    /**
     * 按钮客户区xy坐标
     */
    private int ButtonClientPosy;
    /**
     * 发送消息参数
     */
    private int messageWparam;
    /**
     * 发送消息参数
     */
    private int messageLparam;
    /**
     * 备注信息
     */
    private String remark;
}
