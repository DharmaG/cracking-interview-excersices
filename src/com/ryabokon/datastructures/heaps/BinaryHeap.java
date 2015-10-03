package com.ryabokon.datastructures.heaps;

import java.util.Arrays;

public class BinaryHeap {

	protected int[] array = new int[32];

	int lastIndex = 0;

	public void add(int i) {
		checkCapacity();
		array[lastIndex] = i;
		upHeap(lastIndex);
		lastIndex++;

	}

	public int getRoot() {
		int root = array[0];
		lastIndex--;
		array[0] = array[lastIndex];
		array[lastIndex] = 0;
		downHeap(0);

		return root;

	}

	private void checkCapacity() {
		if (lastIndex == array.length) {
			array = Arrays.copyOf(array, array.length * 2);
		}
	}

	protected void upHeap(int childIndex) {
		int parentIndex = getParentIndex(childIndex);
		if (parentIndex == -1) {
			return;
		}

		if (array[parentIndex] < array[childIndex]) {
			int tmp = array[parentIndex];
			array[parentIndex] = array[childIndex];
			array[childIndex] = tmp;
			upHeap(parentIndex);
		}

	}

	protected void downHeap(int index) {

		if (index >= lastIndex) {
			return;
		}

		int root = array[index];

		int leftChild = 0;
		int rightChild = 0;

		int leftChildIndex = getLeftChildIndex(index);
		int rightChildIndex = getRightChildIndex(index);
		if (leftChildIndex != -1) {
			leftChild = array[leftChildIndex];
		}
		if (rightChildIndex != -1) {
			rightChild = array[rightChildIndex];
		}

		boolean lessThanRight = root < rightChild;
		boolean lessThanLeft = root < leftChild;
		boolean rightIsBigger = rightChild >= leftChild;

		// Covers both || and && cases
		if (lessThanRight || lessThanLeft) {
			// root is smaller than biggest of left / right
			if (rightIsBigger) {
				array[rightChildIndex] = root;
				array[index] = rightChild;
				downHeap(rightChildIndex);
			} else {
				array[leftChildIndex] = root;
				array[index] = leftChild;
				downHeap(leftChildIndex);
			}
		}
	}

	protected int getParentIndex(int childIndex) {
		if (childIndex == 0) {
			return -1;
		}

		// floor( (c - 1) / 2 )
		// No need to use Math.floor with ints.
		return (childIndex - 1) / 2;
	}

	protected int getLeftChildIndex(int parent) {
		int child = 2 * parent + 1;
		if (child > lastIndex) {
			child = -1;
		}

		return child;
	}

	protected int getRightChildIndex(int parent) {
		int child = 2 * parent + 2;
		if (child > lastIndex) {
			child = -1;
		}

		return child;
	}

}
