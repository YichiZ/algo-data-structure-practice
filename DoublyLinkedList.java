public class DoublyLinkedList<T> implements Iterable<T> {

    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    private class Node<T> {
        T data;
        Node<T> prev, next;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    public void clear() {
        Node<T> current = head;
        while (current != null) {
            Node<T> next = current.next;
            current.prev = current.next = null;
            current.data = null;
            current = next;
        }
        head = tail = current = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void add(T elem) {
        addLast(elem);
    }

    public void addFirst(T elem) {
        if (isEmpty()) {
            head = tail = new Node<T>(elem, null, null);
        } else {
            head.prev = new Node<T>(elem, null, head);
            head = head.prev;
        }

        size++;
    }

    public void addLast(T elem) {
        if (isEmpty()) {
            head = tail = new Node<T>(elem, null, null);
        } else {
            tail.next = new Node<T>(elem, tail, null);
            tail = tail.next;
        }

        size++;
    }

    public T peekFirst() {
        if (isEmpty())
            throw new RuntimeException("Empty list");
        return head.data;
    }

    public T peerLast() {
        if (isEmpty())
            throw new RuntimeException("Empty list");
        return tail.data;
    }

    public T removeFirst() {
        if (isEmpty())
            throw new RuntimeException("Empty list");

        T data = head.data;
        head = head.next;
        --size;

        if (isEmpty())
            tail = null;

        else
            head.prev = null;

        return data;
    }

    public T removeLast() {
        if (isEmpty())
            throw new RuntimeException("Empty list");

        T data = tail.data;
        tail = tail.prev;
        --size;

        if (isEmpty())
            head = null;

        else
            tail.next = null;

        return data;
    }

    private T remove(Node<T> node) {
        if (node.prev == null)
            return removeFirst();
        if (node.next == null)
            return removeLast();

        node.next.prev = node.prev;
        node.prev.next = node.next;

        T data = node.data;

        node.data = null;
        node = node.prev = node.next = null;

        --size;

        return data;
    }

    public T removeAt(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException();

        Node<T> trav = head;
        for (int i = 0; i != index; i++)
            trav = trav.next;

        // Possible to use size/2 and iterate from the back to divide time by 2

        return remove(trav);
    }

    public boolean remove(T obj) {
        Node<T> trav = head;

        if (obj == null) {
            for (trav = head; trav != null; trav = trav.next) {
                if (trav.data == null) {
                    remove(trav);
                    return true;
                }
            }
        } else {
            for (trav = head; trav != null; trav = trav.next) {
                if (obj.equals(trav.data)) {
                    remove(trav);
                    return true;
                }
            }
        }

        return false;
    }

    public int indexOf(T obj) {
        int index = 0;
        Node<T> trav = head;

        if (obj == null) {
            for (trav = head; trav != null; trav = trav.next) {
                if (trav.data == null)
                    return index;
            }
        } else {
            for (trav = head; trav != null; trav = trav.next) {
                if (obj.equals(trav.data))
                    return index;
            }
        }

        return -1;
    }

    public boolean contains(T obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            private Node<T> trav = head;

            @Override
            public boolean hasNext() {
                return trav != null;
            }

            @Override
            public T next() {
                T data = trav.data;
                trav = trav.next;
                return data;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        Node<T> trav = head;
        while (trav != null) {
            sb.append(trav.data + ", ");
            trav = trav.next;
        }
        sb.append(" ]");
        return sb.toString();
    }
}
