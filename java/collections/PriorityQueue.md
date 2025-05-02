A `Priority Queue` in `Java` is a special type of `queue` where each element is associated with a `priority`. Elements with `higher priority` are `dequeued` (removed) before elements with `lower priority`. This is a key difference from a `regular queue`, which processes elements in the order they were added `(FIFO - First In, First Out)`. Essentially, a priority queue prioritizes tasks or data based on importance, ensuring that the most critical items are handled first. If multiple elements have the same priority in `Priority Queue`, they are dequeued in FIFO (first-in, first-out) order.

Here's how to use PriorityQueue in `Java`:


```java
import java.util.PriorityQueue;
import java.util.Comparator;

public class PriorityQueueExample {
    public static void main(String[] args) {
        // Creating a min-heap priority queue (default)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(3);
        minHeap.offer(1);
        minHeap.offer(4);
        System.out.println("Min-Heap: " + minHeap); // Output: Min-Heap: [1, 3, 4]

        // Creating a max-heap priority queue using a custom comparator
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        maxHeap.offer(3);
        maxHeap.offer(1);
        maxHeap.offer(4);
        System.out.println("Max-Heap: " + maxHeap);   // Output: Max-Heap: [4, 1, 3]

        // Basic operations
        System.out.println("Peek: " + minHeap.peek()); // Output: Peek: 1
        System.out.println("Poll: " + minHeap.poll()); // Output: Poll: 1
        System.out.println("Queue after poll: " + minHeap); // Output: Queue after poll: [3, 4]
        System.out.println("Size: " + minHeap.size()); // Output: Size: 2
        System.out.println("Is empty: " + minHeap.isEmpty()); // Output: Is empty: false
    }
}
```

Key characteristics of Java `PriorityQueue`:

:one: It is based on a heap data structure, which ensures efficient retrieval of the highest/lowest priority element.
:two: By default, it implements a min-heap (smallest element at the top), but it can be customized to implement a max-heap (largest element at the top) using a Comparator.
:three: It allows duplicate elements.
:four: It is not thread-safe; for thread-safe operations, use PriorityBlockingQueue.
:five: It does not guarantee the order of elements with the same priority.
:six: offer(), poll(), peek(), size(), and isEmpty() methods provide the basic queue operations.
