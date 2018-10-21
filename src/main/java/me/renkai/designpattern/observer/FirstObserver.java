package me.renkai.designpattern.observer;

/**
 * Created by Mckay on 2018/2/25.
 */
public class FirstObserver implements Observer {
    private Observable observable;

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("hahahahaha");
    }

    public FirstObserver(Observable observable) {
        this.observable = observable;
        observable.registerObserver(this);
    }
}
