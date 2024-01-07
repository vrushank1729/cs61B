public class LinkedListDeque<T> implements Deque<T> {
    private StuffNode sentinel;
    private int size;
    public class StuffNode {
        public StuffNode prev;
        public T item;
        public StuffNode next;

        public StuffNode(StuffNode prev, T item, StuffNode next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    public LinkedListDeque() {
        sentinel = new StuffNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        sentinel.next = new StuffNode(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    @Override
    public void addLast(T item) {
        sentinel.prev = new StuffNode(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if(size == 0) {
            return null;
        }
        T first_item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return first_item;
    }

    @Override
    public T removeLast() {
        if(size == 0) {
            return null;
        }
        T last_item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return last_item;
    }

    @Override
    public void printDeque() {
        StuffNode p = sentinel;

        while(p.next != sentinel) {
            System.out.print(p.next.item + " ");
            p = p.next;
        }
    }

    @Override
    public T get(int index) {
        StuffNode p = sentinel;
        index += 1;

        while(p.next != sentinel) {
            if(index == 0) {
                return p.next.item;
            }
            p = p.next;
            index--;
        }

        return null;
    }

    private T getRecursiveHelper(int index, StuffNode p, int p_index) {
        if(p_index == index || p == sentinel) return p.item;

        return getRecursiveHelper(index, p.next, p_index + 1);
    }

    public T getRecursive(int index) {
        T item = getRecursiveHelper(index, sentinel.next, 0);
        return item;
    }
}