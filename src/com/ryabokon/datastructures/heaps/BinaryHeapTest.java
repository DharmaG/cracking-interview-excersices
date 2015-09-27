package com.ryabokon.datastructures.heaps;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Test;

public class BinaryHeapTest {

	@Test
	public void testAddWithoutUpHeap() {

		BinaryHeap heap = new BinaryHeap();

		heap.add(100);
		heap.add(19);
		heap.add(36);
		heap.add(17);
		heap.add(3);
		heap.add(25);
		heap.add(1);

		int[] expected = new int[32];
		expected[0] = 100;
		expected[1] = 19;
		expected[2] = 36;
		expected[3] = 17;
		expected[4] = 3;
		expected[5] = 25;
		expected[6] = 1;

		Assert.assertArrayEquals(expected, heap.array);

	}

	@Test
	public void testAddUpHeap() {

		BinaryHeap heap = new BinaryHeap();

		heap.add(1);
		heap.add(25);
		heap.add(3);
		heap.add(17);
		heap.add(36);
		heap.add(19);
		heap.add(100);

		int[] expected = new int[32];
		expected[0] = 100;
		expected[1] = 25;
		expected[2] = 36;
		expected[3] = 1;
		expected[4] = 17;
		expected[5] = 3;
		expected[6] = 19;

		Assert.assertArrayEquals(expected, heap.array);

	}

	@Test
	public void testCapacity() {
		BinaryHeap heap = new BinaryHeap();
		for (int i = 0; i < 100; i++) {
			heap.add(RandomUtils.nextInt(1, 1000));
		}

		Assert.assertEquals(128, heap.array.length);
	}
}
