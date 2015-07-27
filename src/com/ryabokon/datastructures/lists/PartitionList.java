package com.ryabokon.datastructures.lists;

import org.junit.Assert;
import org.junit.Test;

/**
 * Write code to partition a linked list around a value x, such that all nodes
 * less than x come before all nodes greater than or equal to x
 * ---------------------------------------------------------------------------
 * Can be done by iterating through the list and saving nodes to 2 another
 * lists: less-than-divider and more-than-divider. And then merging those 2
 * lists.
 */
public class PartitionList {

	Node partitionAroundX(Node head, String x) {
		Node less = null;
		Node more = null;
		Node current = head;

		while (current != null) {

			if (x.compareTo(current.data) > 0) {
				less = extractNode(less, current);
			} else if (x.compareTo(current.data) == 0) {
				// If list has duplicate partition values, they should go
				// to the beginning of a "more-than" list.
				Node partition = new Node(current.data);
				partition.next = more;
				more = partition;
			} else {
				more = extractNode(more, current);
			}
			current = current.next;
		}

		// Join less and more
		if (less == null) {
			return more;
		} else {
			Node lastOfLess = less;
			while (lastOfLess.next != null) {
				lastOfLess = lastOfLess.next;
			}
			lastOfLess.next = more;
			return less;
		}

	}

	private Node extractNode(Node destination, Node source) {
		if (destination == null) {
			destination = new Node(source.data);
		} else {
			destination.add(source.data);
		}
		return destination;
	}

	@Test
	public void testWithAsserts() {
		Node head = new Node("a");
		Node n1 = head.add("e");
		Node n2 = head.add("c");
		Node n3 = head.add("b");
		Node n4 = head.add("d");
		Node n5 = head.add("a");
		Node n6 = head.add("c");
		Node n7 = head.add("b");
		Node n8 = head.add("f");

		Node partitioned = partitionAroundX(head, "c");

		Assert.assertEquals("[a, b, a, b, c, c, e, d, f]", partitioned.toString());
	}
}
