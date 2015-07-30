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
public class SumOfNumbersReverse {

	/**
	 * Sum for numbers stored in a reverse way: 123 = 3->2->1
	 */
	public Node<Integer> addReverseNumbers(Node<Integer> numOne, Node<Integer> numTwo) {
		Node<Integer> result = null;

		int tmp = 0;

		while (numOne != null || numTwo != null) {

			int a, b;

			// If the end of a list is reached, use 0 as next elements of a list
			if (numOne == null) {
				a = 0;
			} else {
				a = numOne.data;
				numOne = numOne.next;
			}

			if (numTwo == null) {
				b = 0;
			} else {
				b = numTwo.data;
				numTwo = numTwo.next;
			}

			int sum = a + b + tmp;
			tmp = 0;

			if (sum >= 10) {
				tmp = 1;
				sum -= 10;
			}

			if (result == null) {
				result = new Node<Integer>(sum);
			} else {
				result.add(sum);
			}

		}

		return result;
	}

	@Test
	public void testReverseWithAsserts() {
		Node<Integer> foo = new Node<Integer>(1);
		foo.add(2).add(3).add(4);

		Node<Integer> bar = new Node<Integer>(1);
		bar.add(1).add(9).add(8).add(3).add(3).add(3);

		Node<Integer> res = addReverseNumbers(foo, bar);

		Assert.assertEquals("[2, 3, 2, 3, 4, 3, 3]", res.toString());

	}
}
