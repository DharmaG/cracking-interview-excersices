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
 *
 */
public class SumOfNumbers {

	/**
	 * Sum for numbers going in a common way 123 = 1->2->3
	 */
	public Node<Integer> addNumbers(Node<Integer> numOne, Node<Integer> numTwo) {
		Node<Integer> result = new Node<>(-1);

		int sizeOne = getListSize(numOne);
		int sizeTwo = getListSize(numTwo);

		if (sizeOne != sizeTwo) {
			Node<Integer> listToBePadded = (sizeOne > sizeTwo) ? numTwo : numOne;
			for (int i = 0; i < Math.abs(sizeOne - sizeTwo); i++) {
				Node<Integer> zero = new Node<>(0);
				zero.next = listToBePadded;
				listToBePadded = zero;
			}

			if (sizeOne > sizeTwo) {
				numTwo = listToBePadded;
			} else {
				numOne = listToBePadded;
			}
		}

		recursiveSum(numOne, numTwo, result, true);

		return result;
	}

	public int recursiveSum(Node<Integer> numOne, Node<Integer> numTwo, Node<Integer> partialResult, boolean isItHead) {

		if (numOne == null && numTwo == null) {
			return 0;
		}

		int a = (numOne == null) ? 0 : numOne.data;
		int b = (numTwo == null) ? 0 : numTwo.data;

		numOne = (numOne == null) ? null : numOne.next;
		numTwo = (numTwo == null) ? null : numTwo.next;

		int carry = recursiveSum(numOne, numTwo, partialResult, false);

		// Reached the end of a list, time to sum

		int sum = a + b + carry;
		if (sum >= 10) {
			carry = 1;
			sum -= 10;
		} else {
			carry = 0;
		}

		if (partialResult.data == -1) {
			partialResult.data = sum;
		} else {
			partialResult.add(sum);

		}

		// Add the final carry if present
		if (isItHead && carry != 0) {
			partialResult.add(carry);
		}

		return carry;
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
		Node<Integer> foo = new Node<Integer>(5);
		foo.add(2).add(3).add(4);

		Node<Integer> bar = new Node<Integer>(1);
		bar.add(1).add(9).add(8).add(3).add(3).add(3);

		Node<Integer> res = addNumbers(foo, bar);

		// TODO reverse result or create result by adding numbers at the head side
		Assert.assertEquals("[1, 2, 0, 3, 5, 6, 7]", res.toString());

	}
}
