package com.prokudin.array;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.UnaryOperator;

public class MyArray<Type> implements List<Type> {

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
    public boolean contains(Object value) {
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
    public Iterator<Type> iterator() {
        // TODO: implement
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[items.length];
        copyArray(items, 0, items.length - 1, result, 0);
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < items.length) {
            return (T[]) toArray();
        }
        copyArray(items, 0, items.length - 1, a, 0);
        return a;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Type> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Type> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void replaceAll(UnaryOperator<Type> operator) {

    }

    @Override
    public void sort(Comparator<? super Type> c) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Type set(int index, Type element) {
        return null;
    }

    @Override
    public void add(int index, Type element) {

    }

    @Override
    public Type remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<Type> listIterator() {
        return null;
    }

    @Override
    public ListIterator<Type> listIterator(int index) {
        return null;
    }

    @Override
    public List<Type> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public Spliterator<Type> spliterator() {
        return null;
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
