package me.renkai.designpattern.decorator;

/**
 * Created by Mckay on 2018/3/5.
 */
public class Knife extends AbstractWeapon {

    public Knife() {
        super.weapon = "刀";
    }

    @Override
    public int cost() {
        return 30;
    }
}
