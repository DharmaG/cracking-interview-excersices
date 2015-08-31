package com.ryabokon.datastructures.trees;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {

	public TreeNode(int data) {
		this.data = data;
	}

	Integer data;
	TreeNode left;
	TreeNode right;

	public void add(int data) {

		if (data == this.data) {
			this.data = data;
		} else if (data > this.data) {
			if (right == null) {
				right = new TreeNode(data);
			} else {
				right.add(data);
			}
		} else {
			if (left == null) {
				left = new TreeNode(data);

			} else {
				left.add(data);
			}
		}
	}

	public TreeNode binarySearch(int data) {
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

	public TreeNode depthSearch(int data) {
		if (this.data == data) {
			return (this);
		}

		if (this.left != null) {
			TreeNode result = left.depthSearch(data);
			if (result != null) {
				return result;
			}
		}

		if (this.right != null) {
			TreeNode result = right.depthSearch(data);
			if (result != null) {
				return result;
			}
		}

		return null;
	}

	public TreeNode breadthSearch(int data) {

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(this);

		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
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
