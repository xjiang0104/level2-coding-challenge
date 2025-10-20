/*
 * this file is used to implement a bounded queue
 * it supports put and take operations with blocking behavior
 * it also supports marking the queue as done to signal no more data will be added
 * the queue uses wait/notify for synchronization between producer and consumer threads
 * the queue holds Number objects
 * the capacity of the queue is fixed at creation time
 * if the queue is full, put operation will block until space is available
 * if the queue is empty, take operation will block until data is available or marked done
 * if the queue is empty and marked done, take operation will return null
 * the queue uses a Deque to store the elements
 * the methods are synchronized to ensure thread safety
 */
package pc;

import java.util.ArrayDeque;
import java.util.Deque;

public class BoundedQueue {
    private final int capacity;
    private final Deque<Number> deque = new ArrayDeque<>();
    private boolean done = false;

    public BoundedQueue(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("capacity must be > 0");
        this.capacity = capacity;
    }

    public synchronized void put(Number n) throws InterruptedException {
        while (deque.size() >= capacity) {
            wait();                 // queue full -> wait for consumer
        }
        deque.addLast(n);
        notifyAll();                // queue state changed -> notify consumer
    }

    /** synchronized function to take one element each time */
    public synchronized Number takeOrNullIfDone() throws InterruptedException {
        while (deque.isEmpty() && !done) {
            wait();                 // queue empty and not done -> wait for producer
        }
        if (deque.isEmpty() && done) return null;
        Number n = deque.removeFirst();
        notifyAll();                // queue state changed -> notify producer
        return n;
    }

    public synchronized void markDone() {
        done = true;
        notifyAll();                // notify consumers waiting for data
    }
}
