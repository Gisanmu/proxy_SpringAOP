package pers.sanmu.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import pers.sanmu.proxy.IProducer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        //要在匿名内部类使用就得使用final修饰
        final Producer producer = new Producer();


        /**
         * 动态代理
         *  特点：字节码随用随创建，随用随加载
         *  作用：不修改源码对方法进行增强
         *  分类：
         * ​		基于接口的动态代理：
         * ​		涉及的类：Proxy
         * ​		创建代理对象：使用Proxy类中的newProxyInstance方法
         * ​		创建代理对象的要求：被代理类最少实现一个接口，如果没有则不能使用
         *
         *  create方法参数：
         *      Class:字节码
         *         用于指定被代理对象的字节码
         *
         *      callback：用于提供增强的代码
         *          我们一般写的都是该接口的子接口实现类：MethodInterceptor
         * ​		基于子类的动态代理：
         *          涉及的类：Enhancer
         *          提供者：第三方cglib库
         *      如何创建对象：
         *          使用Enhancer类中的create方法
         *      创建代理对象的要求:
         *          被代理类不能是最终类
         */

        Producer producer1 = (Producer) Enhancer.create(producer.getClass(), new MethodInterceptor() {
            /**
             *
             * @param o
             * @param method
             * @param args
             * @param methodProxy
             * @return
             * @throws Throwable
             */
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                //提供增强代码
                Object returnValue = null;
                //1.获取方法执行的参数
                Float money = (Float)args[0];
                //2.判断当前方法是不是销售
                if ("saleProduct".equals(method.getName())){
                    System.out.println(method.getName());
                    returnValue = method.invoke(producer,money*0.8f);
                }
                return returnValue;
            }
        });

        producer.saleProduct(10000f);

    }
}
