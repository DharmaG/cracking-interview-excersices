package com.ryabokon.datastructures.trees;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {

	public BinarySearchTree(int data) {
		this.data = data;
	}

	Integer data;
	BinarySearchTree left;
	BinarySearchTree right;
	int heigth;

	public void add(int data) {

		if (data == this.data) {
			this.data = data;
		} else if (data > this.data) {
			if (right == null) {
				right = new BinarySearchTree(data);
			} else {
				right.add(data);
			}
		} else {
			if (left == null) {
				left = new BinarySearchTree(data);

			} else {
				left.add(data);
			}
		}
	}

	public BinarySearchTree binarySearch(int data) {
		if (data == this.data) {
			return (this);
		}

		if (data > this.data) {
			if (this.right != null) {
				return right.binarySearch(data);
			}
		} else {
			if (this.left != null) {
				return left.binarySearch(data);
			}
		}

		return null;
	}

	public BinarySearchTree depthSearch(int data) {
		if (this.data == data) {
			return (this);
		}

		if (this.left != null) {
			BinarySearchTree result = left.depthSearch(data);
			if (result != null) {
				return result;
			}
		}

		if (this.right != null) {
			BinarySearchTree result = right.depthSearch(data);
			if (result != null) {
				return result;
			}
		}

		return null;
	}

	public BinarySearchTree breadthSearch(int data) {

		Queue<BinarySearchTree> queue = new LinkedList<>();
		queue.add(this);

		while (!queue.isEmpty()) {
			BinarySearchTree node = queue.poll();
			if (node.data == data) {
				return node;
			}

			if (node.left != null) {
				queue.add(node.left);
			}

			if (node.right != null) {
				queue.add(node.right);
			}
		}

		return null;

	}

	@Override
	public String toString() {
		return String.valueOf(data);
	}
}
