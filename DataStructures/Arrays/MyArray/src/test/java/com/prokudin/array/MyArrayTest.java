package com.prokudin.array;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyArrayTest {

    @Test
    public void checkToString() {
        MyArray<Integer> array = new MyArray<>();
        assertEquals("check empty array", "MyArray{ size = 0, items = [] }", array.toString());

        array = new MyArray<>(new Integer[]{ 1, 2, 3, 4 });
        assertEquals("check empty array", "MyArray{ size = 4, items = [1, 2, 3, 4] }", array.toString());
    }
}