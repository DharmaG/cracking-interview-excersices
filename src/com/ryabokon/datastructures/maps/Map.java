package com.ryabokon.datastructures.maps;

public class Map {

	int size = 32;
	Node[] table = new Node[size];

	public void put(String key, String val) {
		Node n = new Node(key, val);
		int scaledKey = key.hashCode() % size;

		if (table[scaledKey] == null) {
			table[scaledKey] = n;
		} else {

			boolean isKeyAlreadyThere = false;
			Node root = table[scaledKey];
			while (root != null) {
				if (root.key.equals(key)) {
					isKeyAlreadyThere = true;
					root.val = val;
					break;
				}
				root = root.next;

			}

			if (!isKeyAlreadyThere) {
				table[scaledKey].add(n);
			}
		}
	}

	public String get(String key) {

		String result = null;

		int scaledKey = key.hashCode() % size;
		Node n = table[scaledKey];

		while (n != null) {
			if (n.key.equals(key)) {
				result = n.val;
				break;
			}

			n = n.next;
		}

		return result;
	}
}
