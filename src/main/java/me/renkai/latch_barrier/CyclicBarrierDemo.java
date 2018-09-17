package me.renkai.latch_barrier;

import org.omg.PortableServer.THREAD_POLICY_ID;

import java.sql.SQLOutput;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(3,
            () -> System.out.println("3个人开始逛街！"));

    private static class PreparedHangOut {
        PreparedHangOut(String name) {
            new Thread(() -> {
                int bathTime = new Random().nextInt(5) + 1;
                int makeUpTime = new Random().nextInt(5) + 1;
                try {
                    System.out.println(name + "洗澡" + bathTime + "秒");
                    Thread.sleep(bathTime * 1000);
                    System.out.println(name + "化妆" + makeUpTime + "秒");
                    Thread.sleep(makeUpTime * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    System.out.println(name + "准备好了");
                    cyclicBarrier.await();
                    //如果await后面有代码，先执行CyclicBarrier的barrierAction，再执行await后面的代码
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public static void main(String[] args) {
        new PreparedHangOut("小美");
        new PreparedHangOut("丽丽");
        new PreparedHangOut("阿静");
    }
}
