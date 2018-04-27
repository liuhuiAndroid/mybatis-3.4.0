package com.lh.demo.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by lh on 2018/4/27.
 * JDK动态代理
 * InvocationHandler示例实现
 */
public class TestInvokerHandler implements InvocationHandler {

    private Object target;

    public TestInvokerHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("TestInvokerHandler.invoke before");
        Object result = method.invoke(target, args);
        System.out.println("TestInvokerHandler.invoke after");
        return result;
    }

    public Object getProxy() {

        return Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(), // 加载动态生成的代理类的类加载器
                target.getClass().getInterfaces(), // 业务类实现的接口
                this // InvocationHandler对象
        );
    }

    public static void main(String[] args) {
        Subject subject = new RealSubject();
        TestInvokerHandler invokerHandler = new TestInvokerHandler(subject);
        Subject proxy = (Subject) invokerHandler.getProxy();
        proxy.operation();
    }


    interface Subject {
        void operation();
    }

    static class RealSubject implements Subject {
        public void operation() {
            System.out.println("RealSubject.log");
        }
    }
}
