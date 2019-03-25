package me.renkai.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
    public static final Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new R());
        executor.execute(new R());
        executor.shutdown();
    }

    private static class R implements Runnable {

        @Override
        public void run() {
            try {
                lock.lock();
                Thread.sleep(1000);
                System.out.println("done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
