
/*
 * the Producer class is a thread that produces Number objects from a SourceContainer
 * and puts them into a BoundedQueue.
 * the producer thread runs until all items from the source have been produced. 
 * after finishing, it marks the queue as done to signal consumers that no more data will be added.
 */
package pc;

public class Producer extends Thread {
    private final SourceContainer source;
    private final BoundedQueue queue;

    public Producer(SourceContainer source, BoundedQueue queue) {
        super("Producer");
        setDaemon(true);
        this.source = source;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (Number n : source.items()) {
                queue.put(n);       // queue will block if full
            }
        } catch (InterruptedException e) {
            interrupt();
        } finally {
            queue.markDone();       // notify consumers no more data will come
        }
    }
}
