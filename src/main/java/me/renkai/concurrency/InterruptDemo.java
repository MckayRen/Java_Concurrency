package me.renkai.concurrency;

public class InterruptDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupt();
                System.out.println(Thread.currentThread().isInterrupted());
            }
        });

        thread.start();
        thread.interrupt();
    }
}
