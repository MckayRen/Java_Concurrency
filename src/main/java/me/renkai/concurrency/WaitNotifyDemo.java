package me.renkai.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WaitNotifyDemo {
    private static class WaitNotify {
        synchronized void waitMe() {
            System.out.println("我先来了");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("走吧");
        }

        synchronized void notifyMe() {
            notify();
            System.out.println("通知你了");
        }
    }

    public static void main(String[] args) {
        WaitNotify waitNotify = new WaitNotify();
        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(waitNotify::waitMe);
        executorService.execute(waitNotify::notifyMe);

        executorService.execute(waitNotify::waitMe);
        executorService.execute(waitNotify::notifyMe);

        executorService.shutdown();
    }
}
