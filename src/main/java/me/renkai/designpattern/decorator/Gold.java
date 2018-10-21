package me.renkai.designpattern.decorator;

/**
 * Created by Mckay on 2018/3/5.
 */
public class Gold extends WeaponOrnament {
    private AbstractWeapon weapon;

    public Gold(AbstractWeapon weapon) {
        this.weapon = weapon;
    }
    @Override
    public String getWeapon() {
        return weapon.getWeapon() + "+黄金";
    }

    @Override
    public int cost() {
        return weapon.cost() + 50;
    }
}
