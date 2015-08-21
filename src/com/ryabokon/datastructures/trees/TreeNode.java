package com.ryabokon.datastructures.trees;

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

	@Override
	public String toString() {
		return String.valueOf(data);
	}
}
