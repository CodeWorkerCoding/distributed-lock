package com.edu.nchu.distributed.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by fujainjian on 2016/11/13.
 */
public class User implements Serializable {

    @Setter
    @Getter
    private String id;

    @Setter
    @Getter
    private String username;

    @Setter
    @Getter
    private String password;

    @Setter
    @Getter
    private String mobile;

    /**
     * 默认构造参数
     */
    public User() {

    }

    /***
     * 带参数构造方法
     * 
     * @param id
     * @param username
     * @param password
     * @param mobile
     */
    public User(String id, String username, String password, String mobile) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.mobile = mobile;
    }
}
