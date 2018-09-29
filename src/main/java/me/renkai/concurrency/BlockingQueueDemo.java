package me.renkai.concurrency;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {
    private static BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(5);

    private static class Producer implements Runnable {
        @Override
        public void run() {
            try {
                blockingQueue.put(new Random().nextInt(66));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //put() 和 take() 均为阻塞方法

    private static class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println(blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Producer()).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(new Consumer()).start();
        }

        for (int i = 0; i < 3; i++) {
            new Thread(new Producer()).start();
        }
    }
}
