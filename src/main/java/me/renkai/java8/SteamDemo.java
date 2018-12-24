package me.renkai.java8;

import java.util.Random;

public class SteamDemo {
    public static void main(String[] args) {
        Random random = new Random();
        random.ints().limit(6).forEach(System.out::println);

    }
}
