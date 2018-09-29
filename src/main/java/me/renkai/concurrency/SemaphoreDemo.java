package me.renkai.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    public static void main(String[] args) {
        final int permits = 3;
        final int request = 10;

        Semaphore semaphore = new Semaphore(permits);
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < request; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    Thread.sleep(1000);
                    System.out.println("---");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });
        }

        executorService.shutdown();
    }
}
