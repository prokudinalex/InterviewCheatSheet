package com.prokudin.array;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

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

    @Test
    public void checkSimpleRemove() {
        MyArray<Integer> array = new MyArray<>();
        assertEquals("empty array size", 0, array.size());
        array.remove((Integer) 1);
        assertEquals("empty array size", 0, array.size());

        array.add(1);
        assertEquals("empty array add 1 size", 1, array.size());
        assertEquals("check 1 items", "MyArray{ size = 1, items = [1] }", array.toString());
        array.remove((Integer) 1);
        assertEquals("empty array size after remove", 0, array.size());
        assertEquals("check items after remove", "MyArray{ size = 0, items = [] }", array.toString());
    }

    @Test
    public void checkComplexRemove() {
        MyArray<Integer> array = new MyArray<>(new Integer[]{ 1, 2, 3, 3, null, 4 });
        assertEquals("array size", 6, array.size());
        assertEquals("check items before remove", "MyArray{ size = 6, items = [1, 2, 3, 3, null, 4] }", array.toString());

        boolean result = array.remove(null);
        assertTrue(result);
        assertEquals("array size", 5, array.size());
        assertEquals("check items after remove 1", "MyArray{ size = 5, items = [1, 2, 3, 3, 4] }", array.toString());

        result = array.remove((Integer) 3);
        assertTrue(result);
        assertEquals("array size", 4, array.size());
        assertEquals("check items after remove 2", "MyArray{ size = 4, items = [1, 2, 3, 4] }", array.toString());

        array.add(3);
        assertEquals("array size", 5, array.size());
        assertEquals("check items after remove 3", "MyArray{ size = 5, items = [1, 2, 3, 4, 3] }", array.toString());

        result = array.remove((Integer) 3);
        assertTrue(result);
        assertEquals("array size", 4, array.size());
        assertEquals("check items after remove 4", "MyArray{ size = 4, items = [1, 2, 4, 3] }", array.toString());

        result = array.remove((Integer) 3);
        assertTrue(result);
        assertEquals("array size", 3, array.size());
        assertEquals("check items after remove 5", "MyArray{ size = 3, items = [1, 2, 4] }", array.toString());

        result = array.remove((Integer) 1);
        assertTrue(result);
        assertEquals("array size", 2, array.size());
        assertEquals("check items after remove 6", "MyArray{ size = 2, items = [2, 4] }", array.toString());

        result = array.remove((Integer) 4);
        assertTrue(result);
        assertEquals("array size", 1, array.size());
        assertEquals("check items after remove 7", "MyArray{ size = 1, items = [2] }", array.toString());

        result = array.remove((Integer) 2);
        assertTrue(result);
        assertEquals("array size", 0, array.size());
        assertEquals("check items after remove 8", "MyArray{ size = 0, items = [] }", array.toString());
    }

    @Test
    public void checkSimpleAccess() {
        MyArray<Integer> array = new MyArray<>(new Integer[]{ 1, 2, 3, 4 });
        assertEquals("element on 0", (Integer) 1, array.get(0));
        assertEquals("element on 2", (Integer) 3, array.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void checkOutOfBoundsAccessLeftBorder() {
        MyArray<Integer> array = new MyArray<>(new Integer[]{ 1, 2, 3, 4 });
        array.get(-5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void checkOutOfBoundsAccessRightBorder() {
        MyArray<Integer> array = new MyArray<>(new Integer[]{ 1, 2, 3, 4 });
        array.get(10);
    }

    @Test
    public void checkContainsAll() {
        MyArray<Integer> array = new MyArray<>(new Integer[]{ 1, 2, 3, 4 });

        assertTrue(array.containsAll(Collections.emptyList()));
        assertTrue(array.containsAll(Collections.singletonList(1)));

        assertTrue(array.containsAll(Arrays.asList(1, 2)));
        assertTrue(array.containsAll(Arrays.asList(1, 2, 3)));
        assertTrue(array.containsAll(Arrays.asList(1, 2, 3, 4)));
        assertTrue(array.containsAll(Arrays.asList(4, 3, 1, 2)));

        assertFalse(array.containsAll(Arrays.asList(1, 2, 3, 4, 5)));
        assertFalse(array.containsAll(Collections.singletonList(5)));
    }

    @Test
    public void checkAddAll() {
        MyArray<Integer> array = new MyArray<>(new Integer[]{ 1, 2, 3 });

        array.addAll(Arrays.asList(4, 5));
        assertEquals("size after add all", 5, array.size());
        assertEquals("check array", "MyArray{ size = 5, items = [1, 2, 3, 4, 5] }", array.toString());

        array.addAll(Arrays.asList(100, 200, 300));
        Integer[] expected = new Integer[]{ 1, 2, 3, 4, 5, 100, 200, 300 };
        assertArrayEquals("some original array", expected, array.toArray());
    }

    @Test
    public void checkAddAllAtIndex() {
        MyArray<Integer> array = new MyArray<>(new Integer[]{ 1, 2, 3 });

        // check false cases
        assertFalse("add all on wrong index 1", array.addAll(-1, Arrays.asList(4, 5)));
        assertFalse("add all on wrong index 2", array.addAll(4, Arrays.asList(4, 5)));
        assertFalse("add all on wrong index 3", array.addAll(100, Arrays.asList(4, 5)));

        // check beginning of array
        assertTrue("add all in beginning 1", array.addAll(0, Collections.singletonList(0)));
        Integer[] expected = new Integer[]{ 0, 1, 2, 3 };
        assertArrayEquals(expected, array.toArray());

        assertTrue("add all in beginning 2", array.addAll(0, Arrays.asList(100, 200)));
        expected = new Integer[]{ 100, 200, 0, 1, 2, 3 };
        assertArrayEquals(expected, array.toArray());

        // check adding to the middle
        assertTrue("add all in middle 1", array.addAll(4, Collections.singletonList(-5)));
        expected = new Integer[]{ 100, 200, 0, 1, -5, 2, 3 };
        assertArrayEquals(expected, array.toArray());

        assertTrue("add all in middle 2", array.addAll(3, Arrays.asList(-1, -2)));
        expected = new Integer[]{ 100, 200, 0, -1, -2, 1, -5, 2, 3 };
        assertArrayEquals(expected, array.toArray());

        // check adding to the end
        assertTrue("add all in end 1", array.addAll(9, Collections.singletonList(666)));
        expected = new Integer[]{ 100, 200, 0, -1, -2, 1, -5, 2, 3, 666 };
        assertArrayEquals(expected, array.toArray());

        assertTrue("add all in end 2", array.addAll(10, Arrays.asList(777, 888)));
        expected = new Integer[]{ 100, 200, 0, -1, -2, 1, -5, 2, 3, 666, 777, 888 };
        assertArrayEquals(expected, array.toArray());

        // add after clear
        array.clear();
        assertTrue("add all after clear", array.addAll(0, Arrays.asList(777, 888)));
        expected = new Integer[]{ 777, 888 };
        assertArrayEquals(expected, array.toArray());
    }

    @Test
    public void checkClear() {
        MyArray<Integer> array = new MyArray<>(new Integer[]{ 1, 2, 3 });
        assertEquals("check origin array", "MyArray{ size = 3, items = [1, 2, 3] }", array.toString());

        array.clear();
        assertEquals("check cleared array", "MyArray{ size = 0, items = [] }", array.toString());
        assertEquals("check cleared size", 0, array.size());
    }
}
