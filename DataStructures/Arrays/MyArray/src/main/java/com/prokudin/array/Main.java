package com.prokudin.array;

public class Main {

    public static void main(String[] args) {
        MyArray<Integer> array = new MyArray<>();
        System.out.println(array);

        array = new MyArray<>(new Integer[]{1, 2, 3, 4});
        System.out.println(array);
    }
}
