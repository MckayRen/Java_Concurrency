package me.renkai.designpattern.decorator;

/**
 * Created by Mckay on 2018/3/5.
 */
public class Crystal extends WeaponOrnament {
    private AbstractWeapon weapon;

    public Crystal(AbstractWeapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public String getWeapon() {
        return weapon.getWeapon() + "+水晶";
    }

    @Override
    public int cost() {
        return weapon.cost() + 20;
    }
}
