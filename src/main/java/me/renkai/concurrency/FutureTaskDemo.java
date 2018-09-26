package me.renkai.concurrency;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {

    private static class FutureDemo {
        private final FutureTask<BigInteger> future = new FutureTask<>(() -> {
            BigInteger bigInteger = new BigInteger("0");
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(bigInteger = bigInteger.nextProbablePrime());
            }
            return bigInteger;
        });

        private final Thread thread = new Thread(future);

        void start() {
            thread.start();
        }

        BigInteger get() {
            try {
                Thread.sleep(20L);
                thread.interrupt();
                return future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    public static void main(String[] args) {
        FutureDemo futureDemo = new FutureDemo();
        futureDemo.start();
        System.out.println(futureDemo.get());
    }
}

