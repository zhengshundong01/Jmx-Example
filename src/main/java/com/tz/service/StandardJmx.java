package com.tz.service;

import com.tz.service.StandardJmxMBean;

/**
 * @FileName: StandardJmx
 * @Description: StandardMBean demo
 * @Author: ZhengShunDong
 * @Date: 2020/5/27 15:38
 * @Version: 1.0
 */
public class StandardJmx implements StandardJmxMBean {

    public String sayHello(String msg) {
        System.out.println(msg);
        return msg;
    }
}
