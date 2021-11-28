package com.prokudin.array;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
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

    @Test
    public void checkToArray() {
        Integer[] original = new Integer[]{ 1, 2, 3, 4 };
        MyArray<Integer> array = new MyArray<>(original);
        assertArrayEquals("some original array", original, array.toArray());

        Integer[] empty = new Integer[]{};
        array = new MyArray<>(empty);
        assertArrayEquals("some empty array 1", empty, array.toArray());

        array = new MyArray<>();
        assertArrayEquals("some empty array 2", empty, array.toArray());

        Integer[] original2 = new Integer[]{ 1, 2, 3, 4 };
        MyArray<Integer> array2 = new MyArray<>(original);

        assertArrayEquals("pre-filled original array 1", original2, array2.toArray(new Integer[]{}));
        assertArrayEquals("pre-filled original array 2", original2, array2.toArray(new Integer[3]));

        Integer[] bigger = new Integer[]{ 1, 2, 3, 4, null };
        assertArrayEquals("pre-filled bigger array 3", bigger, array2.toArray(new Integer[5]));
    }

    @Test
    public void checkSimpleAdd() {
        MyArray<Integer> array = new MyArray<>();
        assertEquals("empty array size", 0, array.size());

        array.add(1);
        assertEquals("empty array add 1 size", 1, array.size());
        assertEquals("check 1 items", "MyArray{ size = 1, items = [1] }", array.toString());

        array.add(1);
        assertEquals("empty array add second 1 size", 2, array.size());
        assertEquals("check 2 items", "MyArray{ size = 2, items = [1, 1] }", array.toString());

        array.add(100);
        assertEquals("empty array add 100 size", 3, array.size());
        assertEquals("check 2 items", "MyArray{ size = 3, items = [1, 1, 100] }", array.toString());

        Integer[] expected = new Integer[]{ 1, 1, 100 };
        assertArrayEquals("some original array", expected, array.toArray());
    }
}
