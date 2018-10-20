import java.util.Iterator;

import java.util.NoSuchElementException;

/**
 * Created by hulei on 2018/8/27.
 */
public class PeekingIterator implements Iterator<Integer> {
    Integer next = null;
    Iterator<Integer> iter;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        iter = iterator;
        if (iter.hasNext()) {
            next = iter.next();
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (next == null) {
            throw new NoSuchElementException();
        }
        return next;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if (next == null) {
            throw new NoSuchElementException();
        }
        Integer temp = next;
        next = null;
        if (iter.hasNext()) {
            next = iter.next();
        }
        return temp;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }
}
