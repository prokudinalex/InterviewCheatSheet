package com.prokudin.array;

import java.util.Collection;

public interface IArray<Type> {
    int size();
    boolean isEmpty();

    Type get(int index);
    boolean contains(Type value);
    boolean add(Type value);
    boolean remove(Type value);

    Object[] toArray();
    Type[] toArray(Type[] a);

    boolean containsAll(Collection<Type> c);
    boolean addAll(Collection<? extends Type> c);
    boolean addAll(int index, Collection<? extends Type> c);
    boolean removeAll(Collection<?> c);
    void clear();
    Type set(int index, Type element);
    boolean add(int index, Type element);
    Type remove(int index);
    int indexOf(Object o);
    int lastIndexOf(Object o);
}
