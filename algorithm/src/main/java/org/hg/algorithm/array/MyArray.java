package org.hg.algorithm.array;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @Author huangggang6135@gmail.com
 */
public class MyArray<E> implements Iterable {
    private final int DEFAULT_LENGTH = 16;
    private Object[] array;
    private int size;
    private int capacity;

    public MyArray() {
        array = new Object[16];
        capacity = DEFAULT_LENGTH;
    }

    public MyArray(int capacity) {
        checkCapacity(capacity);
        array = new Object[capacity];

    }

    private void checkCapacity(int capacity) {
        if (size < -1 || size > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("illegal Argument capacity:" + capacity);
        }
    }

    public E get(int index) {
        if (index > size - 1) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return (E) array[index];
    }

    public void insert(E e) {
        ensureCapacity();
        array[size++] = e;
    }

    private void ensureCapacity() {
        if (size + 1 > capacity) {
            grow();
        }
    }

    private void grow() {
        capacity = 2 * capacity + 2;
        array = Arrays.copyOf(array, capacity);
    }

    public void set(E e, int index){
        if(index<0 || index > Integer.MAX_VALUE){
            throw new IllegalArgumentException("Illegal set argument index:" + index);
        }
        get(index);
        array[index] = e;
    }

    public E remove(int index){
        E e = get(index);
        if (removeLast(index)) return e;
        System.arraycopy(array, index+1, array, index, size-1-index);
        array[--size] = null;
        return e;
    }

    private boolean removeLast(int index) {
        if(index == size - 1){
            array[--size] = null;
            return true;
        }
        return false;
    }

    public E removeNoSort(int index){
        E e = get(index);
        if (removeLast(index)) return e;
        array[index] = array[size-1];
        array[--size] = null;
        return e;
    }

    public Object[] toArray(){
        return Arrays.copyOf(array, size);
    }

    public static void main(String[] args) {
        MyArray<Integer> myArray = new MyArray<>(4);
        myArray.insert(1);
        myArray.insert(2);
        myArray.insert(3);
        myArray.insert(4);
        myArray.insert(5);
        myArray.insert(6);
        myArray.insert(7);
        myArray.insert(8);
        System.out.println(Arrays.toString(myArray.toArray()));
//        myArray.remove(9);
//        myArray.remove(-1);
        myArray.remove(2);
        System.out.println(Arrays.toString(myArray.toArray()));
        myArray.removeNoSort(3);
        System.out.println(Arrays.toString(myArray.toArray()));
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Object next() {
                return null;
            }
        };
    }

    @Override
    public void forEach(Consumer action) {

    }

    @Override
    public Spliterator spliterator() {
        return null;
    }
}
