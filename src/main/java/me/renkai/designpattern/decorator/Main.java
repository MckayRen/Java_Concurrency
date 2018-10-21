package me.renkai.designpattern.decorator;

/**
 * Created by Mckay on 2018/3/5.
 */
public class Main {
    public static void main(String[] args) {
        AbstractWeapon sword = new Sword();
        sword = new Gold(sword);
        System.out.println(sword.getWeapon() + " ¥" + sword.cost());


        AbstractWeapon knife = new Crystal(
                new Gold(new Knife())
        );
        System.out.println(knife.getWeapon() + " ¥" + knife.cost());

        Thread.yield();
    }
}
