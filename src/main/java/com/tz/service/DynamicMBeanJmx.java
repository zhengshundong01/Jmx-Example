package com.tz.service;

import javax.management.*;

public class DynamicMBeanJmx implements IDynamicMBeanJmx {

    private MBeanInfo mBeanInfo = null;

    public DynamicMBeanJmx () throws NoSuchMethodException{
        String className = this.getClass().getName();
        String desc = "just a desc";
        MBeanConstructorInfo[] constructors = new MBeanConstructorInfo[1];
        constructors[0] = new MBeanConstructorInfo("constructors desc", this.getClass().getConstructors()[0]);
        MBeanOperationInfo[] operations = new MBeanOperationInfo[2];
        // 两种方式，一种直接使用现有方法
        operations[0] = new MBeanOperationInfo("say", this.getClass().getMethod("say", null));
        // 自己构造一个方法
        operations[1] = new MBeanOperationInfo("sayHello", "methods desc", null, "void", MBeanOperationInfo.INFO);
        mBeanInfo = new MBeanInfo(className, desc, new MBeanAttributeInfo[0], constructors, operations, new MBeanNotificationInfo[0]);
    }
    public Object getAttribute(String attribute) throws AttributeNotFoundException, MBeanException, ReflectionException {
        return null;
    }

    public void setAttribute(Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {

    }

    public AttributeList getAttributes(String[] attributes) {
        return null;
    }

    public AttributeList setAttributes(AttributeList attributes) {
        return null;
    }

    public Object invoke(String actionName, Object[] params, String[] signature) throws MBeanException, ReflectionException {
        if (actionName.equals("sayHello")) {
            System.out.println("You Are Calling SayHello Method Now");
        } else if (actionName.equals("say")) {
            this.say();
        }
        return null;
    }

    public void say() {
        System.out.println("Say: HelloWorld");
    }

    public MBeanInfo getMBeanInfo() {
        return mBeanInfo;
    }
}
