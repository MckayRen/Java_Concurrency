package me.renkai.dynamicproxy;

public class CalculatorImpl implements Calculator {
    @Override
    public void add(int a, int b) {
        System.out.println(a + b);
    }
}
