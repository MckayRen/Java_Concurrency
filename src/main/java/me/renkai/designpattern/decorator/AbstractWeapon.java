package me.renkai.designpattern.decorator;

/**
 * Created by Mckay on 2018/3/5.
 */
public abstract class AbstractWeapon {
    String weapon;

    public String getWeapon() {
        return weapon;
    }

    public abstract int cost();
}
