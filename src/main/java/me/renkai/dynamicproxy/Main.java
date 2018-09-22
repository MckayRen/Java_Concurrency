package me.renkai.dynamicproxy;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        Calculator c = (Calculator) Proxy.newProxyInstance(CalculatorImpl.class.getClassLoader(),
                CalculatorImpl.class.getInterfaces(), new CalculatorInvocationHandler());

        c.add(1, 2);
    }
}
