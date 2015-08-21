package com.ryabokon.datastructures.maps;

public class Node {

	public String key;
	public String val;
	public Node next;

	public Node(String key, String val) {
		this.key = key;
		this.val = val;

	}

	public void add(Node newNode) {

		Node node = this;
		while (node.next != null) {
			node = node.next;
		}
		node.next = newNode;
	}

}
