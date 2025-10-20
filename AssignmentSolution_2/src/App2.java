/*
    This file is used to implement the main application logic
    it creates a SourceContainer with integers and doubles,
    a DestinationContainer with the same capacity,
    a BoundedQueue with half the source capacity,
    and starts Producer and Consumer threads to transfer data
    from the SourceContainer to the DestinationContainer via the BoundedQueue.
    finally, it verifies that the data in the DestinationContainer matches the SourceContainer.
*/
import java.util.List;
import pc.*;

public class App2 {
    public static void main(String[] args) throws Exception {
        // Step 1: Create SourceContainer with integers and doubles
        List<Number> sourceData = List.of(1, 2.5, 3, 4.75, 5, 6.25, 7, 8.5);
        SourceContainer sourceContainer = new SourceContainer(sourceData);
        System.out.println("1) Source container created with data: " + sourceContainer.items());
        System.out.println("   Capacity: " + sourceContainer.capacity());

        // Step 2: Create DestinationContainer with same capacity
        DestinationContainer destinationContainer = new DestinationContainer(sourceContainer.capacity());
        System.out.println("2) Destination container created with capacity = " + destinationContainer.capacity());

        // Step 3: Create a BoundedQueue with half the source capacity
        int queueCapacity = Math.max(1, sourceContainer.capacity() / 2);
        BoundedQueue queue = new BoundedQueue(queueCapacity);
        System.out.println("3) Queue created with capacity = " + queueCapacity);

        // Step 4: Create and start Producer and Consumer
        Producer producer = new Producer(sourceContainer, queue);
        Consumer consumer = new Consumer(destinationContainer, queue);

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        System.out.println("4) Producer and Consumer threads have finished execution.");

        // Step 5: Verify data integrity
        System.out.println("5) Checking results...");
        System.out.println("   Source:      " + sourceContainer.items());
        System.out.println("   Destination: " + destinationContainer.snapshot());
        boolean isEqual = sourceContainer.items().equals(destinationContainer.snapshot());
        System.out.println("   Equal: " + isEqual);

        if (!isEqual) {
            throw new AssertionError("Destination does not match Source!");
        } else {
            System.out.println("All checks passed : Producer/Consumer logic verified.");
        }
    }
}
