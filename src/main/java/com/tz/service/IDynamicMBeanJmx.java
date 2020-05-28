package com.tz.service;

import javax.management.DynamicMBean;

/**
 * @FileName: IDynamicMBeanJmx
 * @Description: 实现接口否则无法正确调用
 * @Author: ZhengShunDong
 * @Date: 2020/5/28 13:44
 * @Version: 1.0
 */
public interface IDynamicMBeanJmx extends DynamicMBean {
    public void say();
}
