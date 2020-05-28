package com.tz;

import com.tz.service.*;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class JmxClientStarter {

    public static void main(String[] args) throws Exception{
        // 创建连接URL
        JMXServiceURL jmxServiceURL = new JMXServiceURL("service:jmx:rmi://localhost:1099/jndi/rmi://localhost:1099/jmx:rmi");
        // 获取连接Connector
        JMXConnector connector = JMXConnectorFactory.connect(jmxServiceURL);
        // 拿到连接
        MBeanServerConnection connection = connector.getMBeanServerConnection();

        // 获取Standard MBean
        StandardJmxMBean standardJmxMBean = JMX.newMBeanProxy(connection, new ObjectName("jmx:name=StandardJmx"), StandardJmxMBean.class);
        // 方法调用，可以去看Server有新打印日志
        standardJmxMBean.sayHello("你好呀");
        System.out.println("获取：" + standardJmxMBean.toString());

        // 获取动态MBean
        IDynamicMBeanJmx dynamicMBeanJmx = MBeanServerInvocationHandler.newProxyInstance(connection, new ObjectName("jmx:name=DynamicMBeanJmx"), IDynamicMBeanJmx.class, false);
        dynamicMBeanJmx.say();
    }
}
