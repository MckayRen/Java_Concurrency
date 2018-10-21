package me.renkai.designpattern.observer;

/**
 * Created by Mckay on 2018/2/25.
 */
public interface Observable {
    void registerObserver(Observer o);

    void quitObserver(Observer o);

    void notifyObserver();
}
