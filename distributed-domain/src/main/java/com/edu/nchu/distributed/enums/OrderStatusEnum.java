package com.edu.nchu.distributed.enums;

/**
 * Created by Alen on 2016/10/30.
 */
public enum OrderStatusEnum {

    INIT("INIT", ""),
    CANCEL("CANCEL", ""),
    COMPLETED("COMPLETED", "");


    private String value;
    private String desc;

    OrderStatusEnum(String value, String desc){
        this.value = value;
        this.desc = desc;
    }

}
