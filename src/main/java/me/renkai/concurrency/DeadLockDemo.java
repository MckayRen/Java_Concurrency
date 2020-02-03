package me.renkai.concurrency;

/**
 * 死锁示例
 * javac -d . DeadLockDemo.class
 * java me.renkai.concurrency.DeadLockDemo
 * top 找到java进程id
 * jstack {pid}
 */
public class DeadLockDemo {
    private static final Object RA = new Object();
    private static final Object RB = new Object();

    public static class A extends Thread {
        @Override
        public void run() {
            synchronized (RA) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (RB) {
                    System.out.println("A获取到了两个锁");
                }
            }
        }
    }

    public static class B extends Thread {
        @Override
        public void run() {
            synchronized (RB) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (RA) {
                    System.out.println("B获取到了两个锁");
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread a = new A();
        Thread b = new B();
        a.start();
        b.start();

        a.join();
        b.join();
    }
}
