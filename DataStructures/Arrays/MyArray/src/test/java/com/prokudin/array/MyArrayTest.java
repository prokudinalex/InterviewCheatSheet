package com.prokudin.array;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
        assertEquals("empty", true, array.isEmpty());

        array = new MyArray<>(new Integer[]{ 1, 2, 3, 4 });
        assertEquals("not empty", false, array.isEmpty());
    }
}
