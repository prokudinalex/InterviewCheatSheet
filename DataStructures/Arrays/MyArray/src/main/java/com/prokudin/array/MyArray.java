package com.prokudin.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class MyArray<Type> {
    private int length;
    private Collection<Type> items = new ArrayList<>();

    public MyArray() {
    }

    public MyArray(Type[] value) {
        this.length = value.length;
        items.addAll(Arrays.asList(value));
    }

    @Override
    public String toString() {
        return String.format("MyArray{ length = %d, items = [%s] }", length,
                items.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", ")));
    }
}
