package com.ryabokon.datastructures.lists;

import org.junit.Assert;
import org.junit.Test;

/**
 * Implement an algorithm to find the kth to last element of a singly linked
 * list.
 * ------------------------------------------------------------------------
 * 
 * While iterating over a linked list, a runner can be launched from the
 * beginning of the list, when kth element is reached.
 * 
 * Null is returned if list length is less than k
 *
 */
public class FindKthLastElement {

	public Node getKthLastElement(Node head, int k) {
		int i = 0;
		Node current = head;
		Node runner = head;

		while (current != null) {
			i++;
			if (i > k) {
				runner = runner.next;
			}
			current = current.next;
		}

		// List size is less than k
		if (i < k) {
			runner = null;
		}

		return runner;
	}

	@Test
	public void test() {
		Node head = new Node("a");
		Node n1 = head.add("b");
		Node n2 = head.add("c");
		Node n3 = head.add("b");
		Node n4 = head.add("d");
		Node n5 = head.add("a");

		Node lastThird = getKthLastElement(head, 3);
		Assert.assertEquals(n3, lastThird);

		Node first = getKthLastElement(head, 6);
		Assert.assertEquals(head, first);

		Node err = getKthLastElement(head, 15);
		Assert.assertEquals(null, err);

	}

}
