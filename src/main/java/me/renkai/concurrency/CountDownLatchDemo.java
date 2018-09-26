package me.renkai.concurrency;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) throws Exception{
        CountDownLatch startGate = new CountDownLatch(1);

        final int threadCount = 1000;
        CountDownLatch endGate = new CountDownLatch(threadCount);
        for (int t = 0; t < threadCount; t++) {
            new Thread(() -> {
                try {
                    startGate.await();
                    for (int i = 0; i< 100000; i ++) {
                        String s = String.valueOf(i);
                    }
                    endGate.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        long start = System.currentTimeMillis();
        startGate.countDown();

        //await()会阻塞直到计数器达到零
        endGate.await();
        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }
}
