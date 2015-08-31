package com.ryabokon.datastructures.trees;

import org.junit.Assert;
import org.junit.Test;

public class TreeNodeTest {

	@Test
	public void test() {

		TreeNode tree = new TreeNode(8);
		tree.add(3);
		tree.add(10);
		tree.add(1);
		tree.add(6);
		tree.add(14);
		tree.add(4);
		tree.add(7);
		tree.add(13);

		for (int i = 0; i < 15; i++) {
			TreeNode binResult = tree.binarySearch(i);
			TreeNode depthResult = tree.depthSearch(i);
			TreeNode breadthResult = tree.breadthSearch(i);

			Assert.assertEquals(binResult, depthResult);
			Assert.assertEquals(depthResult, breadthResult);
		}

	}

}
