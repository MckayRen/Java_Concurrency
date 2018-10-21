package me.renkai.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mckay on 2018/2/25.
 */
public class Main {
    public static void main(String[] args) {
        Admin admin = new Admin();

        Observer o1 = new FirstObserver(admin);
        Observer o2 = new SecondObserver(admin);

        admin.up();

        System.out.println(a());
    }

    public static List<String> a () {
        List<String> a = new ArrayList<>();
        try {
            return a;
        } finally {
            a.add("44");
        }
    }
}
