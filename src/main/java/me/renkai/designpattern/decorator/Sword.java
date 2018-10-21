package me.renkai.designpattern.decorator;

/**
 * Created by Mckay on 2018/3/5.
 */
public class Sword extends AbstractWeapon {

    public Sword() {
        super.weapon = "剑";
    }

    @Override
    public int cost() {
        return 100;
    }
}
