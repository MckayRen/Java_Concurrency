package me.renkai.concurrency;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class CasStack<E> {
    private AtomicReference<Node<E>> stackTop = new AtomicReference<>();

    public void push(E item) {
        Node<E> newTop = new Node<>(item);
        Node<E> oldTop;
        do {
            oldTop = stackTop.get();
            newTop.next = oldTop;
        } while (!stackTop.compareAndSet(oldTop, newTop));
    }

    public E pop() {
        Node<E> oldTop;
        Node<E> newTop;
        do {
            oldTop = stackTop.get();
            if (oldTop == null) {
                return null;
            }
            newTop = oldTop.next;
        } while (!stackTop.compareAndSet(oldTop, newTop));
        return oldTop.item;
    }

    private class Node<E> {
        private E item;
        private Node<E> next;

        private Node(E item) {
            this.item = item;
        }
    }


    public static void main(String[] args) throws InterruptedException {
        CasStack<Integer> stack = new CasStack<>();
        ExecutorService service = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(2);
        Thread p1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                stack.push(i);
                try {
                    Thread.sleep(new Random().nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            latch.countDown();
        });
        service.execute(p1);

        Thread p2 = new Thread(() -> {
            for (int i = 100; i < 200; i++) {
                stack.push(i);
                try {
                    Thread.sleep(new Random().nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            latch.countDown();
        });
        service.execute(p2);

        service.execute(() -> {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int count = 0;
            while (true) {
                Integer v = stack.pop();
                if (v != null) {
                    System.out.println(v);
                    count++;
                } else {
                    break;
                }
            }
            if (count == 200) {
                System.out.println("Good!");
            }
        });

        service.shutdown();
    }
}
