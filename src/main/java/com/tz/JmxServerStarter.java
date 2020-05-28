package com.tz;

import com.tz.service.DynamicJmx;
import com.tz.service.DynamicMBeanJmx;
import com.tz.service.StandardJmx;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


/**  
 * @FileName: JmxServerStarter
 * @Description: TODO
 * @Author: ZhengShunDong
 * @Date: 2020/5/27 16:39
 * @Version: 1.0
 */
public class JmxServerStarter {

    public static void main(String[] args) throws Exception{
        // 创建一个MBeanServer管理Bean
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();

        // 命名
        ObjectName objectName = new ObjectName("jmx:name=StandardJmx");
        // 注册-需要注意的是StandardJmx要和他的接口类在同包里
        // 1、StandardMBean
        server.registerMBean(new StandardJmx(), objectName);

        // 2、DynamicMBean
        server.registerMBean(new DynamicJmx(), new ObjectName("jmx:name=DynamicJmx"));
        server.registerMBean(new DynamicMBeanJmx(), new ObjectName("jmx:name=DynamicMBeanJmx"));
        System.out.println("Success");

        // 开辟端口
        Registry registry = LocateRegistry.createRegistry(1099);
        // JMX连接URL以及底层采用RMI协议
        JMXServiceURL jmxServiceURL = new JMXServiceURL("service:jmx:rmi://localhost:1099/jndi/rmi://localhost:1099/jmx:rmi");

        // 创建
        JMXConnectorServer cs = JMXConnectorServerFactory.newJMXConnectorServer(jmxServiceURL, null, server);
        // 启动
        cs.start();
    }
}
