package me.renkai.concurrency;

public class InterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("thread1 " + Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupt();
                System.out.println("thread2 " + Thread.currentThread().isInterrupted());
                System.out.println(1/0);
            }
        });

        thread.setUncaughtExceptionHandler((Thread t, Throwable e)
                -> System.out.println(t + "  " + e));

        thread.start();
        System.out.println("main1 " + thread.isInterrupted());
        Thread.sleep(1000);
        System.out.println("main2 " + thread.isInterrupted());
        thread.interrupt();
        System.out.println("main3 " + thread.isInterrupted());

    }
}
