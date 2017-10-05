package com.ryabokon.datastructures.stacks;

public interface Stack<T> {

    public T pop();

    public void push(T obj);

    public T peek();

    public boolean isEmpty();

}
