package pers.sanmu.AOP.utils;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 用于记录日志的工具类，里面提供公共代码
 */
public class Logger {
    /**
     * 用于打印日志，计划让其在切入点方法执行之前执行(切入点方法就是业务层的方法)
     */
    public  void beforePrintLog(){
        System.out.println("前置通知打印日志。");
    }

    public  void afterReturningPrintLog(){
        System.out.println("后置通知打印日志。");
    }

    public  void afterThrowingPrintLog(){
        System.out.println("异常通知打印日志。");
    }

    public  void afterPrintLog(){
        System.out.println("最终通知打印日志。");
    }




    /**
     * 环绕通知
     * 当配置了环绕通知之后，切入点方法没有执行，而通知方法执行了
     * 分析：
     *      通过对比动态代理中当环绕通知代码，发现动态代理当环绕通知有明确当切入点方法调用
     * Spring框架中为我们提供了一个接口，ProceedingJoinPoint，该接口有一个方法proceed（），此方法就相当于明确调用切入点方法。
     * 该接口可以昨晚环绕通知当方法参数，在程序执行时，spring框架会为我们提供接口当实现类为我们使用。
     *
     * Spring中的环绕通知：
     *      它是spring框架为我们提供的一种可以在代码中手动控制增强方法何时执行的方式
     */

    public Object aroundPringLog(ProceedingJoinPoint pjp){
        Object rtValue = null;
        try {
            //获取方法执行所需当参数
            Object[] args = pjp.getArgs();

            System.out.println("前置打印日志。");
            //明确调用业务层方法(切入点方法)
            rtValue = pjp.proceed(args);
            System.out.println("后置打印日志。");

            return rtValue;
        }catch (Throwable throwable){
            System.out.println("异常打印日志。");
            throw new RuntimeException(throwable);
        }finally {
            System.out.println("最终打印日志。");
        }
    }
}
