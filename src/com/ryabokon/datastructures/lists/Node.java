package com.ryabokon.datastructures.lists;

public class Node<T> {

	private Node<T> next;
	private T data;
	private int size;

	public Node() {
		this.size = 0;
	}

	public Node(T data) {
		this.size = 1;
		this.data = data;
	}

	public T get(int index) {

		if (index >= size) {
			throw new IndexOutOfBoundsException();
		}

		Node<T> node = this;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}

		return node.data;
	}

	public void add(T data) {
		if (size == 0) {
			this.data = data;
			this.size++;
			return;
		}

		Node<T> newNode = new Node<T>(data);

		Node<T> node = this;
		node.size++;
		while (node.next != null) {
			node = node.next;
			node.size++;
		}
		node.next = newNode;
	}

	public void delete(int index) {
		if (index >= size) {
			throw new IndexOutOfBoundsException();
		}

		if (index == 0) {

			// delete current node
			if (next != null) {
				this.data = next.data;
				this.size = next.size;
				this.next = next.next;
			} else {
				// or erase current node
				this.size = 0;
				this.data = null;
			}

		} else {

			Node<T> previous = this;
			Node<T> current = this;
			for (int i = 0; i < index; i++) {
				if (i == index - 1) {
					previous = current;
				}
				current.size--;
				current = current.next;
			}

			previous.next = current.next;
		}

	}

	public int size() {
		return size;
	}
}
