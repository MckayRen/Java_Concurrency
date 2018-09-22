package me.renkai.dynamicproxy;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CalculatorInvocationHandler implements InvocationHandler {
    private static final Calculator CALCULATOR = new CalculatorImpl();


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("参数:" + StringUtils.join(args, ","));
        method.invoke(CALCULATOR, args);
        return null;
    }
}
