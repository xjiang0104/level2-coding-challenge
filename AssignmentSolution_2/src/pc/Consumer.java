
/*
    This file is used to implement a consumer thread
    it takes Number objects from a BoundedQueue
    and adds them to a DestinationContainer
    the consumer thread runs until it receives a null from the queue,
    which indicates that no more data will be added
*/
package pc;

public class Consumer extends Thread {
    private final DestinationContainer dest;
    private final BoundedQueue queue;

    public Consumer(DestinationContainer dest, BoundedQueue queue) {
        super("Consumer");
        setDaemon(true);
        this.dest = dest;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Number n = queue.takeOrNullIfDone(); // return null if done and empty
                if (n == null) break;
                dest.add(n);
            }
        } catch (InterruptedException e) {
            interrupt();
        }
    }
}
