/*
 * the DestinationContainer class is used to store Number objects up to a specified capacity.
 * it provides synchronized methods to add Number objects and to take a snapshot of the current items.
 * the add method throws an exception if the capacity is exceeded.
 * the snapshot method returns a copy of the current items in the container.
 * the capacity of the container is fixed at creation time.
 * the class ensures thread safety for concurrent access.
 * the items are stored in an ArrayList.
 * the constructor takes the capacity as an argument and validates it.
 */
package pc;

import java.util.ArrayList;
import java.util.List;

public class DestinationContainer {
    private final int capacity;
    private final List<Number> items = new ArrayList<>();

    public DestinationContainer(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("capacity < 0");
        this.capacity = capacity;
    }

    public synchronized void add(Number n) {
        if (items.size() >= capacity) {
            throw new IllegalStateException("Destination capacity exceeded");
        }
        items.add(n);
    }

    public synchronized List<Number> snapshot() {
        return new ArrayList<>(items);
    }

    public int capacity() { return capacity; }
}
