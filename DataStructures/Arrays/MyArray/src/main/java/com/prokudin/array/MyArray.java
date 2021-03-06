package com.prokudin.array;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MyArray<Type> implements IArray<Type> {

    private int size;
    private Object[] items = {};

    public MyArray() {
    }


    public MyArray(Type[] value) {
        items = new Object[value.length];
        copyArray(value, 0, value.length - 1, items, 0);
        size = items.length;
    }

    @Override
    public String toString() {
        return String.format("MyArray{ size = %d, items = %s }", size,
                Arrays.toString(items));
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Just returns the element by index
     * Time complexity: O(1)
     * @param index index of required element
     * @return element at specified index
     */
    @SuppressWarnings("unchecked")
    @Override
    public Type get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (Type) items[index];
    }

    /**
     * That's a search method, so we need to go through the whole array
     * In the worst case the element will be in the same end, so it will take O(n)
     * https://github.com/prokudinalex/InterviewCheatSheet/tree/master/DataStructures/Arrays#time-complexity-of-basic-operations
     * @param value element to search
     * @return true if the element exists in the array
     */
    @Override
    public boolean contains(Type value) {
        for (Object item : items) { // here comes O(n)
            if (value == null && item == null) {
                return true;
            }
            if (value != null && value.equals(item)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds an element to the same end of array.
     * Need to re-allocate array of extended size, and copy elements, so it will take O(n)
     * @param value element to add
     * @return true
     */
    @Override
    public boolean add(Type value) {
        Object[] result = new Object[items.length + 1];
        copyArray(items, 0, items.length - 1, result, 0);
        result[result.length - 1] = value;
        this.size = result.length;
        this.items = result;
        return true;
    }

    /**
     * Removes first occurrence of value from the array.
     * Time complexity: O(n)
     * @param value element to be removed, if exists
     * @return true if array was modified
     */
    @Override
    public boolean remove(Object value) {
        if (size <= 0) {
            return false;
        }

        Object[] result = new Object[items.length - 1];

        boolean found = false;
        for (int i = 0; i < items.length; i++) {
            if (!found && items[i] == value) {
                found = true;
                continue; // just shift the index once
            }

            result[found ? i - 1 : i] = items[i];
        }

        if (found) {
            this.items = result;
            this.size = result.length;
        }
        return found;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[items.length];
        copyArray(items, 0, items.length - 1, result, 0);
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Type[] toArray(Type[] a) {
        if (a.length < items.length) {
            return (Type[]) toArray();
        }
        copyArray(items, 0, items.length - 1, a, 0);
        return a;
    }

    @Override
    public boolean containsAll(Collection<Type> c) {
        Set<Type> expected = new HashSet<>(c); // O(n)

        for (Object item : items) { // O(m)
            expected.remove(item); // Java HashSet remove takes O(1) time
        }
        return expected.size() == 0; // 0(1), So in overall O(n + m)
    }

    @Override
    public boolean addAll(Collection<? extends Type> c) {
        // copy origin
        Object[] result = new Object[items.length + c.size()];
        copyArray(items, 0, items.length - 1, result, 0);

        // copy tail
        Object[] src = c.toArray();
        copyArray(src, 0, src.length - 1, result, items.length);
        this.size = result.length;
        this.items = result;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Type> c) {
        if (index < 0 || index > items.length) {
            return false;
        }

        // copy head
        Object[] result = new Object[items.length + c.size()];
        copyArray(items, 0, index - 1, result, 0);

        // copy source
        Object[] src = c.toArray();
        copyArray(src, 0, src.length - 1, result, index);

        // copy tail
        copyArray(items, index, items.length - 1, result, index + src.length);

        this.size = result.length;
        this.items = result;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // TODO: implement
        return false;
    }

    @Override
    public void clear() {
        items = new Object[0];
        size = 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Type set(int index, Type element) {
        if (index < 0 || index >= items.length) {
            return null;
        }

        Type old = (Type) items[index];
        items[index] = element;
        return old;
    }

    @Override
    public boolean add(int index, Type element) {
        if (index < 0 || index > items.length) {
            return false;
        }

        // copy head
        Object[] result = new Object[items.length + 1];
        copyArray(items, 0, index - 1, result, 0);

        // copy source
        result[index] = element;

        // copy tail
        copyArray(items, index, items.length - 1, result, index + 1);

        this.size = result.length;
        this.items = result;
        return true;
    }

    @Override
    public Type remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < items.length; i++) {
            if (null == o && null == items[i]) {
                return i;
            }

            if (null != items[i] && items[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = items.length - 1; i >= 0; i--) {
            if (null == o && null == items[i]) {
                return i;
            }

            if (null != items[i] && items[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Copy data from source array to destination
     * Time Complexity: O(n)
     * @param src source array to copy from
     * @param srcStart inclusive index of source array to start from
     * @param srcEnd inclusive index of source array to finish at
     * @param dst destination array where to the elements should be copied
     * @param dstStart inclusive index of the destination array to start from
     * @param <Type> type of elements
     */
    private static <Type> void copyArray(Type[] src, int srcStart, int srcEnd,
                                         Type[] dst, int dstStart) {
        // validate source input
        if (srcStart > srcEnd || srcStart >= src.length || srcStart < 0) {
            return;
        }

        // validate destination input
        if (dstStart >= dst.length || dstStart < 0) {
            return;
        }

        for (int s = srcStart, d = dstStart; s <= srcEnd && d < dst.length; s++, d++) {
            dst[d] = src[s];
        }
    }
}
