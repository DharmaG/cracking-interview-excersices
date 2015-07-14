package com.ryabokon.datastructures.lists;

import org.junit.Assert;
import org.junit.Test;

/**
 * Implement an algorithm to delete a node in the middle of a singly linked list, given
 * only access to that node.
 * 
 * ----------------------------------------------
 * 
 * Can make this by replacing node's data/ref with next node's data/ref
 * And ref to original next node will be automatically lost
 *
 * However, this will not work if last node is being deleted
 * Can't access to parent's refs this way.
 * So, node can have some placeholder data to indicate that it's empty
 *
 */
public class DeleteCurrentNode {

	@Test
	public void testWithAsserts() {
		Node head = new Node("a");
		Node removed = head.add("del");
		Node third = head.add("c");

		deleteCurrentNode(removed);

		String result = head.toString();
		Assert.assertEquals("[a, c]", result);

		deleteCurrentNode(removed);
		result = head.toString();
		Assert.assertEquals("[a, EMPTY]", result);

	}

	public void deleteCurrentNode(Node n) {
		if (n == null) {
			return;
		}

		if (n.next != null) {
			n.data = n.next.data;
			n.next = n.next.next;
		} else {
			// "del" reference value is passed to method
			// and this doesn't give us any control on a head's ref to "del"

			// instead, we can mark this node as empty
			n.data = "EMPTY";
		}

	}
}
