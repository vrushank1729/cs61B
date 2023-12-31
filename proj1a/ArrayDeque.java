public class ArrayDeque<T> {
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    public ArrayDeque() {
        items = (T []) new Object[100];
        size = 0;
        nextLast = 0;
        nextFirst = 99;
    }

    private void resize(int capacity) {
        T[] new_items = (T []) new Object[capacity];
        System.arraycopy(items, 0, new_items, 0, nextLast);
        int nextFirst_length = (items.length - 1) - nextFirst;
        System.arraycopy(items, nextFirst + 1, new_items, capacity - nextFirst_length, nextFirst_length);
        items = new_items;
        nextFirst = (capacity - nextFirst_length) - 1;
    }

    public void addFirst(T item) {
        if(size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        if(nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst -= 1;
        }
        size += 1;
    }

    public void addLast(T item) {
        if(size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        if(nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public T removeFirst() {
        if(size == 0) {
            return null;
        }
        T first;
        if(nextFirst == items.length - 1) {
            first = items[0];
            nextFirst = 0;
        } else {
            first = items[nextFirst + 1];
            nextFirst += 1;
        }
        size -= 1;
        return first;
    }

    public T removeLast() {
        if(size == 0) {
            return null;
        }
        T last;
        if(nextLast == 0) {
            last = items[items.length - 1];
            nextLast = items.length - 1;
        } else {
            last = items[nextLast - 1];
            nextLast -= 1;
        }
        size -= 1;
        return last;
    }

    public T get(int index) {
        int i = nextFirst + 1;
        if(i == items.length) {
            i = 0;
        }

        while (!(i < nextFirst && i > nextLast)) {
            if(index == 0) {
                return items[i];
            }
            i++;
            if(i == items.length) {
                i = 0;
            }
            index--;
        }

        return null;
    }

    public void printDeque() {
        int i = nextFirst + 1;
        if(i == items.length) {
            i = 0;
        }

        while (!(i < nextFirst && i > nextLast)) {
            System.out.print(items[i] + " ");
            i++;
            if(i == items.length) {
                i = 0;
            }
        }
    }
}