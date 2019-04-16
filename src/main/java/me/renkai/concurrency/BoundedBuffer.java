package me.renkai.concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有界缓存
 */
public final class BoundedBuffer<V> {
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    //数组实现循环队列
    private final V[] buffer;
    private int capacity;
    private int rear;
    private int front;

    public BoundedBuffer(int capacity) {
        if (capacity <= 1) {
            throw new IndexOutOfBoundsException("must bigger than 1");
        }
        this.capacity = capacity;
        this.buffer = (V[]) new Object[capacity];
        this.rear = 0;
        this.front = 0;
    }

    private void enqueue(V v) {
        lock.lock();
        try {
            while (full()) {
                notFull.await();
            }
            buffer[rear] = v;
            rear = (rear + 1) % capacity;
            notEmpty.signal();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    private V dequeue() {
        lock.lock();
        try {
            while (empty()) {
                notEmpty.await();
            }
            V v = buffer[front];
            buffer[front] = null;
            front = (front + 1) % capacity;
            notFull.signal();
            return v;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } finally {
            lock.unlock();
        }
    }

    private boolean full() {
        lock.lock();
        try {
            return ((rear + 1) % capacity) == front;
        } finally {
            lock.unlock();
        }
    }

    private boolean empty() {
        lock.lock();
        try {
            return rear == front;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        BoundedBuffer<String> b = new BoundedBuffer<>(4);

        ExecutorService executors = Executors.newCachedThreadPool();
        executors.submit(() -> {
            for (int i = 0; i < 100; i++) {
                b.enqueue(i + "");
                try {
                    Thread.sleep(new Random().nextInt(50));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        executors.submit(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(b.dequeue());
                try {
                    Thread.sleep(new Random().nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executors.shutdown();
    }

}
