public class LinkedListDeque<T> {
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

    public void addFirst(T item) {
        sentinel.next = new StuffNode(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    public void addLast(T item) {
        sentinel.prev = new StuffNode(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public T removeFirst() {
        T first_item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return first_item;
    }

    public T removeLast() {
        T last_item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return last_item;
    }

    public void printDeque() {
        StuffNode p = sentinel;

        while(p.next != sentinel) {
            System.out.print(p.next.item + " ");
            p = p.next;
        }
    }

    public T get(int index) {
        StuffNode p = sentinel;
        int pnext_index = 0;

        while(p.next != sentinel || pnext_index != index) {
            p = p.next;
            pnext_index++;
        }

        return p.next.item;
    }

    private T getRecursive(int index, StuffNode p, int p_index) {
        if(p_index == index || p == sentinel) return p.item;

        return getRecursive(index, p.next, p_index + 1);
    }

    public T getRecursive(int index) {
        T item = getRecursive(index, sentinel.next, 0);
        return item;
    }
}