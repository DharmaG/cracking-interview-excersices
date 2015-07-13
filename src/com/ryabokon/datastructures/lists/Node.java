package com.ryabokon.datastructures.lists;

/**
 * My implementation of a LinkedList
 */
public class Node {

	protected Node next;
	protected String data;

	public Node(String data) {
		this.data = data;
	}

	public Node add(String data) {

		Node newNode = new Node(data);

		Node node = this;
		while (node.next != null) {
			node = node.next;
		}
		node.next = newNode;

		return newNode;
	}

	@Override
	public String toString() {

		String response = "[";
		Node node = this;
		while (node != null) {
			response = response + node.data + ", ";
			node = node.next;
		}

		response = response.substring(0, response.length() - 2) + "]";

		return response;

	}
}
