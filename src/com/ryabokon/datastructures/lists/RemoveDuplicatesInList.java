package com.ryabokon.datastructures.lists;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Write code to remove duplicates from an unsorted linked list.
 * 
 * FOLLOW UP
 * 
 * How would you solve this problem if a temporary buffer is not allowed?
 * ----------------------------------------------------------------------
 * 
 * Set can be used to store iterated items, and to check for duplicates while
 * iterating.
 * 
 * Or a runner can be used to iterate over all next nodes and to check them vs
 * current node.
 * 
 * For big amount of duplicates, set and runner solutions are quite equal. As
 * list gets smaller fast after each runner loop.
 * 
 * But when there is a small amount of duplicates, set solution is faster.
 *
 */
public class RemoveDuplicatesInList {

	int iterations = 1000;
	static String[] elements;
	static int listSize = 1000;

	public void removeDuplicatesSet(Node head) {

		Set<String> duplicates = new HashSet<>();

		Node current = head;
		duplicates.add(current.data);

		// Delete only next nodes,
		// this will allow not to store a previous node separately.
		while (current.next != null) {
			if (duplicates.contains(current.next.data)) {
				current.next = current.next.next;
			} else {
				duplicates.add(current.next.data);
				current = current.next;
			}
		}

	}

	public void removeDuplicatesNoBuffer(Node head) {

		Node current = head;
		Node runner = head;

		while (current != null) {

			String currentValue = current.data;

			runner = current;
			while (runner.next != null) {
				if (currentValue.equals(runner.next.data)) {
					runner.next = runner.next.next;
				} else {
					runner = runner.next;
				}
			}

			current = current.next;
		}

	}

	@Test
	public void testSetWithAsserts() {

		Node head = new Node("a");
		Node n1 = head.add("b");
		Node n2 = head.add("c");
		Node n3 = head.add("b");
		Node n4 = head.add("d");
		Node n5 = head.add("a");

		removeDuplicatesSet(head);
		Assert.assertEquals(n4, n2.next);
		Assert.assertEquals(null, n4.next);

		String result = head.toString();
		Assert.assertEquals("[a, b, c, d]", result);

	}

	@Test
	public void testNoBufferWithAsserts() {
		Node head = new Node("a");
		Node n1 = head.add("b");
		Node n2 = head.add("c");
		Node n3 = head.add("b");
		Node n4 = head.add("d");
		Node n5 = head.add("a");

		removeDuplicatesNoBuffer(head);
		Assert.assertEquals(n4, n2.next);
		Assert.assertEquals(null, n4.next);

		String result = head.toString();
		Assert.assertEquals("[a, b, c, d]", result);

	}

	// -----------Performance testing-----------------------------------

	@BeforeClass
	public static void initElements() {
		elements = new String[listSize];
		for (int i = 0; i < listSize; i++) {
			elements[i] = RandomStringUtils.random(100, "qwertyuiop");
		}
	}

	@Test
	public void testSetPerformance() {

		for (int i = 0; i < iterations; i++) {

			Node head = makeListFromElements();
			removeDuplicatesSet(head);
		}

	}

	@Test
	public void testNoBufferPerformance() {

		for (int i = 0; i < iterations; i++) {
			Node head = makeListFromElements();
			removeDuplicatesNoBuffer(head);
		}

	}

	private Node makeListFromElements() {
		Node head = new Node("s");
		for (int i = 0; i < listSize; i++) {
			head.add(elements[i]);
		}
		return head;
	}
}
