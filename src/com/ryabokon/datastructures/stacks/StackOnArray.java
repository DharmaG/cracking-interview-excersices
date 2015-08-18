package com.ryabokon.datastructures.stacks;

import java.util.Arrays;

public class StackOnArray<T> implements Stack<T> {

	T[] stack = (T[]) new Object[32];
	int currentPosition = 0;

	@Override
	public T pop() {
		if (currentPosition == 0) {
			return null;
		}
		return stack[--currentPosition];
	}

	@Override
	public void push(T obj) {
		checkCapacity();
		stack[currentPosition++] = obj;
	}

	private void checkCapacity() {
		if (currentPosition == stack.length) {
			stack = Arrays.copyOf(stack, stack.length * 2);
		}

	}

}
