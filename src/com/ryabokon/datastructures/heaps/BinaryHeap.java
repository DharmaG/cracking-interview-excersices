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
		// TODO implement
		return 0;

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

	protected int getParentIndex(int childIndex) {
		if (childIndex == 0) {
			return -1;
		}

		// floor( (c - 1) / 2 )
		// No need to use Math.floor with ints.
		return (childIndex - 1) / 2;
	}

}
