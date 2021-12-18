package com.prokudin.array;

import java.util.Collection;
import java.util.Comparator;
import java.util.function.UnaryOperator;

public interface IArray<Type> {
    int size();
    boolean isEmpty();

    Type get(int index);
    boolean contains(Type value);
    boolean add(Type value);
    boolean remove(Type value);

    Object[] toArray();
    <T> T[] toArray(T[] a);


    boolean containsAll(Collection<?> c);
    boolean addAll(Collection<? extends Type> c);
    boolean addAll(int index, Collection<? extends Type> c);
    boolean removeAll(Collection<?> c);
    boolean retainAll(Collection<?> c);
    void replaceAll(UnaryOperator<Type> operator);
    void sort(Comparator<? super Type> c);
    void clear();
    Type set(int index, Type element);
    void add(int index, Type element);
    Type remove(int index);
    int indexOf(Object o);
    int lastIndexOf(Object o);
}
