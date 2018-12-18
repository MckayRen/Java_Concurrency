package me.renkai.java8;

import java.util.ArrayList;
import java.util.List;

public class LambdaDemo {
    public static void main(String[] args) {
        In i1 = (a, b) -> a + b;
        System.out.println(i1.twoNumber(1, 2));


        List<String> names = new ArrayList<>();
        names.add("me");
        names.forEach(System.out::println);
    }


    private interface In {
        int twoNumber(int a, int b);
    }
}
