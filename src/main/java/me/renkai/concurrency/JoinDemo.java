package me.renkai.concurrency;

public class JoinDemo {
    private static class A extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("AAA");
        }
    }

    private static class B extends Thread {
        private A a;

        B(A a) {
            this.a = a;
        }

        @Override
        public void run() {
            try {
                //在当前线程中调用另一个线程的 join() 方法，会将当前线程挂起，等待被调用的另一个线程结束。
                a.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("BBB");
        }
    }

    public static void main(String[] args) {
        A a = new A();
        B b = new B(a);

        b.start();
        a.start();

    }
}
