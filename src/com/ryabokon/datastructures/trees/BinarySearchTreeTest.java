package com.ryabokon.datastructures.trees;

import org.junit.Assert;
import org.junit.Test;

public class BinarySearchTreeTest {

	@Test
	public void test() {

		BinarySearchTree tree = new BinarySearchTree(8);
		tree.add(3);
		tree.add(10);
		tree.add(1);
		tree.add(6);
		tree.add(14);
		tree.add(4);
		tree.add(7);
		tree.add(13);

		for (int i = 0; i < 15; i++) {
			BinarySearchTree binResult = tree.binarySearch(i);
			BinarySearchTree depthResult = tree.depthSearch(i);
			BinarySearchTree breadthResult = tree.breadthSearch(i);

			Assert.assertEquals(binResult, depthResult);
			Assert.assertEquals(depthResult, breadthResult);
		}

	}

}
