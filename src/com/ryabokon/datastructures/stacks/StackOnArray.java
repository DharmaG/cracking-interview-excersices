package com.ryabokon.datastructures.stacks;

import java.util.Arrays;

public class StackOnArray<T> implements Stack<T> {

    T[] stack = (T[]) new Object[32];
    int currentPosition = -1;

    @Override
    public T pop() {
        if (currentPosition == -1) {
            return null;
        }

        T result = stack[currentPosition];
        stack[currentPosition] = null;
        currentPosition--;
        return result;
    }

    @Override
    public T peek() {
        if (currentPosition == -1) {
            return null;
        }
        return stack[currentPosition];
    }

    @Override
    public void push(T obj) {
        checkCapacity();
        stack[++currentPosition] = obj;
    }

    @Override
    public boolean isEmpty() {
        return currentPosition == -1;
    }

    private void checkCapacity() {
        if (currentPosition == stack.length - 1) {
            stack = Arrays.copyOf(stack, stack.length * 2);
        }

    }

}
