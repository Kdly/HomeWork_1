public class CustomHashMap<K, V> {
    private static final double LOAD_FACTOR = 0.75;

    private Node<K, V>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public CustomHashMap() {
        buckets = (Node<K, V>[]) new Node<?, ?>[16];
    }

    public V get(K key) {
        int index = getIndex(key);
        Node<K, V> current = buckets[index];

        while (current != null) {
            if (keysEqual(current.key, key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public V put(K key, V value) {
        int index = getIndex(key);
        Node<K, V> current = buckets[index];

        while (current != null) {
            if (keysEqual(current.key, key)) {
                V oldValue = current.value;
                current.value = value;
                return oldValue;
            }
            current = current.next;
        }

        if (size + 1 > buckets.length * LOAD_FACTOR) {
            resize();
            index = getIndex(key);
        }

        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = buckets[index];
        buckets[index] = newNode;
        size++;
        return null;
    }

    public V remove(K key) {
        int index = getIndex(key);
        Node<K, V> current = buckets[index];
        Node<K, V> previous = null;

        while (current != null) {
            if (keysEqual(current.key, key)) {
                if (previous == null) {
                    buckets[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return current.value;
            }
            previous = current;
            current = current.next;
        }
        return null;
    }

    public int size() {
        gi
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean keysEqual(K firstKey, K secondKey) {
        if (firstKey == null) {
            return secondKey == null;
        }
        return firstKey.equals(secondKey);
    }

    private int getIndex(K key) {
        if (key == null) {
            return 0;
        }
        return Math.abs(key.hashCode() % buckets.length);
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Node<K, V>[] oldBuckets = buckets;
        buckets = (Node<K, V>[]) new Node<?, ?>[oldBuckets.length * 2];
        size = 0;

        for (Node<K, V> bucket : oldBuckets) {
            Node<K, V> current = bucket;
            while (current != null) {
                put(current.key, current.value);
                current = current.next;
            }
        }
    }

    private static class Node<K, V> {
        private final K key;
        private V value;
        private Node<K, V> next;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
