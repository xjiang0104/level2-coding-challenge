import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import pc.*;

public class TestProducerConsumer {
    public static void main(String[] args) throws Exception {
        testSmall();
        testLargeRandom();
        System.out.println("[OK] All tests passed.");
    }

    private static void testSmall() throws Exception {
        List<Number> data = List.of(0, -1, 3.14, 42, 2_000_000_000, -2.71828, 9999999999L);
        runAndAssert(data);
    }

    private static void testLargeRandom() throws Exception {
        Random r = new Random(42);
        List<Number> data = new ArrayList<>();
        for (int i = 0; i < 10_000; i++) {
            if (r.nextBoolean()) data.add(r.nextInt(1_000_000));
            else data.add(r.nextDouble() * 1_000_000d);
        }
        runAndAssert(data);
    }

    private static void runAndAssert(List<Number> data) throws Exception {
        SourceContainer src = new SourceContainer(data);
        DestinationContainer dest = new DestinationContainer(src.capacity());
        int qCap = Math.max(1, src.capacity() / 2);
        BoundedQueue q = new BoundedQueue(qCap);

        Producer p = new Producer(src, q);
        Consumer c = new Consumer(dest, q);
        p.start();
        c.start();
        p.join();
        c.join();

        List<Number> out = dest.snapshot();
        assert out.equals(src.items()) : "Mismatch between source and destination";
    }
}
