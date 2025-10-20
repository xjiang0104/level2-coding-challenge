/*
 * the SourceContainer class is used to hold a fixed list of Number objects.
 * it provides methods to get the capacity and to retrieve the items.
 * the items are stored in an unmodifiable list to ensure immutability. 
 * the constructor takes a list of Number objects and creates an unmodifiable copy.
 * the capacity is determined by the size of the provided list.
 * the class ensures thread safety by not allowing modifications to the items list.
 * the items method returns the unmodifiable list of Number objects.
 * the capacity method returns the number of items in the container.
 */
package pc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SourceContainer {
    private final List<Number> items;

    public SourceContainer(List<Number> items) {
        this.items = Collections.unmodifiableList(new ArrayList<>(items));
    }
    public int capacity() { return items.size(); }
    public List<Number> items() { return items; }
}
