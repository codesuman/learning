import java.util.LinkedList;
import java.util.Objects;

class CustomHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private LinkedList<Node<K, V>>[] buckets;
    private int size;
    private int capacity;

    public CustomHashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public CustomHashMap(int capacity, float loadFactor) {
        this.capacity = capacity;
        this.buckets = new LinkedList[capacity];
        this.size = 0;
    }

    public void put(K key, V value) {
        int index = getBucketIndex(key);
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }

        LinkedList<Node<K, V>> bucket = buckets[index];
        for (Node<K, V> node : bucket) {
            if (Objects.equals(node.key, key)) {
                node.value = value;
                return;
            }
        }
        bucket.add(new Node<>(key, value));
        size++;

        if ((float) size / capacity >= DEFAULT_LOAD_FACTOR) {
            resize();
        }
    }

    public V get(K key) {
        int index = getBucketIndex(key);
        if (buckets[index] != null) {
            for (Node<K, V> node : buckets[index]) {
                if (Objects.equals(node.key, key)) {
                    return node.value;
                }
            }
        }
        return null;
    }

    public void remove(K key) {
        int index = getBucketIndex(key);
        if (buckets[index] != null) {
            buckets[index].removeIf(entry -> Objects.equals(entry.key, key));
            size--;
        }
    }

    public int size() {
        return size;
    }

    private int getBucketIndex(K key) {
        return Objects.isNull(key) ? 0 : Math.abs(key.hashCode() % capacity);
    }

    private void resize() {
        capacity *= 2;
        LinkedList<Node<K, V>>[] newBuckets = new LinkedList[capacity];
        for (LinkedList<Node<K, V>> bucket : buckets) {
            if (bucket != null) {
                for (Node<K, V> entry : bucket) {
                    int newIndex = getBucketIndex(entry.key);
                    if (newBuckets[newIndex] == null) {
                        newBuckets[newIndex] = new LinkedList<>();
                    }
                    newBuckets[newIndex].add(entry);
                }
            }
        }
        buckets = newBuckets;
    }

    static class Node<K, V> {
        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}