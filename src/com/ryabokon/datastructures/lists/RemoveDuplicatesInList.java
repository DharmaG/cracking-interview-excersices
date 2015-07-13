package com.ryabokon.datastructures.lists;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * Write code to remove duplicates from an unsorted linked list.
 * 
 * FOLLOW UP
 * 
 * How would you solve this problem if a temporary buffer is not allowed?
 *
 */
public class RemoveDuplicatesInList {

	public void removeDuplicatesSetAndPrevious(Node head) {

		Set<String> duplicates = new HashSet<>();

		Node current = head;
		Node previous = head;

		while (current != null) {
			if (duplicates.contains(current.data)) {
				previous.next = current.next;
				current = current.next;
			} else {
				duplicates.add(current.data);
				previous = current;
				current = current.next;
			}
		}

	}

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
	public void testSetPrevious() {
		Node head = new Node("a");
		Node n1 = head.add("b");
		Node n2 = head.add("c");
		Node n3 = head.add("b");
		Node n4 = head.add("d");
		Node n5 = head.add("a");

		removeDuplicatesSetAndPrevious(head);
		Assert.assertEquals(n4, n2.next);
		Assert.assertEquals(null, n4.next);

		String result = head.toString();
		Assert.assertEquals("[a, b, c, d]", result);

	}

	@Test
	public void testSet() {
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
	public void testNoBuffer() {
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
}
