package com.primerxiao.freestyle.common;

/**
 * 大漠字库信息
 * @author primerxiao
 */
public enum DmZkEnum {
    /**
     * t2box中的开始游戏按钮
     */
    T2_KAISHIYOUXI("开始游戏","#0-000000"),
    T2_JIEQIUDIDAII("街球地带","#255-000000"),
    T2_DOUNIUSHENGDI("斗牛圣地","#255-000000"),
    T2_XIHABULUO("嘻哈部落","#255-000000"),
    ;
    String name;
    String color;
    DmZkEnum(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }
}
