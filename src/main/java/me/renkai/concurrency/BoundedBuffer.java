package me.renkai.concurrency;

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
        b.enqueue("66");
        b.enqueue("77");
        b.enqueue("88");
        System.out.println(b.dequeue());
        System.out.println(b.dequeue());
        b.enqueue("99");
        b.enqueue("00");
        System.out.println(b.dequeue());
        System.out.println(b.dequeue());
        System.out.println(b.dequeue());
    }

}
