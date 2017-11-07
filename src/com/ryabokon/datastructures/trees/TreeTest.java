package com.ryabokon.datastructures.trees;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TreeTest {

    List<Integer> numbers = Arrays.asList(3, 10, 1, 6, 14, 4, 7, 13);
    BinarySearchTree tree = numbers.stream().collect(BinarySearchTree::new, BinarySearchTree::add, (a, b) -> {});

    @Test
    public void test() {


        for (Integer i = 0; i < 15; i++) {
            BinarySearchTree binResult = tree.binarySearch(i);
            BinarySearchTree binRecursiveResult = tree.binarySearchRecursive(i);
            BinarySearchTree depthResult = tree.depthFirstSearch(i);
            BinarySearchTree breadthResult = tree.breadthFirstSearch(i);

            if (numbers.contains(i)) {
                Assert.assertEquals(i, binResult.data);
                Assert.assertEquals(i, binRecursiveResult.data);
                Assert.assertEquals(i, depthResult.data);
                Assert.assertEquals(i, breadthResult.data);
            } else {
                Assert.assertNull(binResult);
                Assert.assertNull(binRecursiveResult);
                Assert.assertNull(depthResult);
                Assert.assertNull(breadthResult);
            }
        }
    }

    @Test
    public void testBalance() {
        Assert.assertFalse(tree.isBalanced());
        Assert.assertTrue(tree.right.isBalanced());

        tree.add(8);
        tree.add(9);
        Assert.assertFalse(tree.right.isBalanced());

    }

}
