package com.ryabokon.datastructures.lists;

/**
 * My implementation of a LinkedList
 */
public class Node<T> {

	public Node<T> next;
	public T data;

	public Node(T data) {
		this.data = data;
	}

	public Node<T> add(T data) {

		Node<T> newNode = new Node<T>(data);

		Node<T> node = this;
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
