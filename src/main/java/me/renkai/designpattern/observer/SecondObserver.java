package me.renkai.designpattern.observer;

/**
 * Created by Mckay on 2018/2/25.
 */
public class SecondObserver implements Observer {
    private Observable observable;

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("noooooooo");
    }

    public SecondObserver(Observable observable) {
        this.observable = observable;
        observable.registerObserver(this);
    }
}
