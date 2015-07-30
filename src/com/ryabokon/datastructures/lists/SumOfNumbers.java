package com.ryabokon.datastructures.lists;

import org.junit.Assert;
import org.junit.Test;

/**
 * You have two numbers represented by a linked list, where each node contains a
 * single digit. The digits are stored in reverse order, such that the 1 's
 * digit is at the head of the list. Write a function that adds the two numbers
 * and returns the sum as a linked list.
 * 
 * FOLLOW UP Suppose the digits are stored in forward order. Repeat the above
 * problem.
 * -----------------------------------------------------------------------------
 * Can be solved in a natural way like sum of numbers is calculated by hand
 * 1)Sum digits while iterating through both lists
 * 
 * 2)If result is > 10, add (result-10) carry to the next sum
 * 
 * 3)If one list is out of numbers when other still has them, use 0s instead of
 * first list numbers
 *
 */
public class SumOfNumbers {

	/**
	 * Sum for numbers going in a common way 123 = 1->2->3
	 */
	public Node<Integer> addNumbers(Node<Integer> numOne, Node<Integer> numTwo) {
		Node<Integer> result = null;

		int sizeOne = getListSize(numOne);
		int sizeTwo = getListSize(numTwo);

		if (sizeOne != sizeTwo) {
			Node numToPadd = (sizeOne > sizeTwo) ? numTwo : numOne;
			for (int i = 0; i < Math.abs(sizeOne - sizeTwo); i++) {
				Node<Integer> zero = new Node<>(0);
				zero.next = numToPadd;
				numToPadd = zero;
			}
		}

		// TODO

		return result;
	}

	public int getListSize(Node node) {
		int size = 0;
		while (node != null) {
			size++;
			node = node.next;
		}

		return size;
	}

	@Test
	public void testReverseWithAsserts() {
		Node<Integer> foo = new Node<Integer>(1);
		foo.add(2).add(3).add(4);

		Node<Integer> bar = new Node<Integer>(1);
		bar.add(1).add(9).add(8).add(3).add(3).add(3);

		Node<Integer> res = addNumbers(foo, bar);

		Assert.assertEquals("[2, 3, 2, 3, 4, 3, 3]", res.toString());

	}
}
