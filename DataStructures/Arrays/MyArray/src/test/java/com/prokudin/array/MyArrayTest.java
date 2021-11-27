package com.prokudin.array;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MyArrayTest {

    @Test
    public void checkToString() {
        MyArray<Integer> array = new MyArray<>();
        assertEquals("check empty array", "MyArray{ size = 0, items = [] }", array.toString());

        array = new MyArray<>(new Integer[]{ 1, 2, 3, 4 });
        assertEquals("check 4 items", "MyArray{ size = 4, items = [1, 2, 3, 4] }", array.toString());
    }

    @Test(expected = NullPointerException.class)
    public void checkOnNullCreation() {
        new MyArray<>(null); // should throw NPE
    }

    @Test
    public void checkSize() {
        MyArray<Integer> array = new MyArray<>();
        assertEquals("empty size", 0, array.size());

        array = new MyArray<>(new Integer[]{ 1, 2, 3, 4 });
        assertEquals("4 items", 4, array.size());

        final int ARRAY_SIZE = 100000;
        array = new MyArray<>(new Integer[ARRAY_SIZE]);
        assertEquals(String.format("%d items", ARRAY_SIZE), ARRAY_SIZE, array.size());
    }

    @Test
    public void checkEmpty() {
        MyArray<Integer> array = new MyArray<>();
        assertTrue("empty", array.isEmpty());

        array = new MyArray<>(new Integer[]{ 1, 2, 3, 4 });
        assertFalse("not empty", array.isEmpty());
    }

    @Test
    public void checkContains() {
        MyArray<Integer> array = new MyArray<>(new Integer[]{ 1, 2, 3, 4 });
        assertTrue("1 contains 3", array.contains(3));
        assertFalse("1 contains 10", array.contains(10));
        assertFalse("1 contains null", array.contains(null));

        array = new MyArray<>(new Integer[]{ 1, null, 3, 0 });
        assertTrue("2 contains null", array.contains(null));
        assertTrue("2 contains 0", array.contains(0));
        assertFalse("2 contains 10", array.contains(10));
    }
}
