package me.renkai.java8;

import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) {
        Optional<String> name = Optional.of("me");
        System.out.println(name.isPresent() + " " + name.get());


        try {
            Optional.of(null);
        } catch (NullPointerException npe) {
            System.out.println("of方法只为非null的值创建一个Optional");
        }


        Optional<String> empty = Optional.ofNullable(null);
        System.out.println(empty.isPresent());


        //如果Optional值不为空，则调用Consumer的accept()方法
        name.ifPresent((value) -> System.out.println(value.toUpperCase()));


        //orElse，如果有值直接返回，否则返回指定的参数值
        System.out.println(name.orElse("I am empty"));
        System.out.println(empty.orElse("I am empty"));


        //orElseGet与orElse类似，区别在于返回的默认值，orElseGet可以接受Supplier接口的实现来生成默认值
        System.out.println(empty.orElseGet(() -> String.valueOf(Integer.MAX_VALUE)));

        try {
            //orElseThrow，如果有值则返回，无值则抛出异常
            empty.orElseThrow(IllegalArgumentException::new);
        } catch (IllegalArgumentException i) {
            //unchecked exceptions
            System.out.println();
        }


        //map
        Optional<Integer> me = name.map((value) -> (int) value.charAt(0));
        System.out.println(me.get());

        Optional<String> me2 = name.flatMap((value) -> Optional.of(value.toLowerCase()));
        System.out.println(me2.get());
    }

}
