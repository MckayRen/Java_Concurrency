package me.renkai.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mckay on 2018/2/25.
 */
public class Admin implements Observable {
    private List<Observer> observers;

    public Admin() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void quitObserver(Observer o) {
        this.observers.remove(o);
    }

    @Override
    public void notifyObserver() {
        for (Observer o :
                observers) {
            o.update(this, "股价上涨了");
        }
    }

    public void up() {
        this.notifyObserver();
    }
}
