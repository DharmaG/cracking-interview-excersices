package com.ryabokon.datastructures.trees;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class BinarySearchTree {
    Integer data;

    BinarySearchTree left;
    BinarySearchTree right;

    private boolean isBalanced(BinarySearchTree tree) {

        if (tree == null) {
            return true;
        }

        int leftHeight = getHeight(tree.left);
        int rightHeight = getHeight(tree.right);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        }

        return isBalanced(tree.left) && isBalanced(tree.right);
    }

    private int getHeight(BinarySearchTree tree) {
        if (tree == null) {
            return 0;
        }

        int leftResult = tree.left == null ? 0 : getHeight(tree.left);
        int rightResult = tree.right == null ? 0 : getHeight(tree.right);

        if (Math.abs(leftResult - rightResult) > 1) {
            throw new RuntimeException("Tree is unbalanced");
        }

        return Math.max(leftResult, rightResult) + 1;
    }

    public void add(Integer data) {
        if (this.data == null) {
            this.data = data;
            return;
        }

        if (data > this.data) {
            if (this.right == null) {
                this.right = new BinarySearchTree();
            }
            right.add(data);
        } else {
            if (this.left == null) {
                this.left = new BinarySearchTree();
            }
            left.add(data);
        }
    }

    public BinarySearchTree binarySearchRecursive(Integer data) {
        if (Objects.equals(this.data, data)) {
            return this;
        }

        if (data > this.data) {
            return right == null ? null : right.binarySearchRecursive(data);
        } else {
            return left == null ? null : left.binarySearchRecursive(data);
        }
    }

    public BinarySearchTree binarySearch(Integer data) {
        if (Objects.equals(this.data, data)) {
            return this;
        }

        BinarySearchTree tree = this;

        while (tree != null) {
            if (Objects.equals(tree.data, data)) {
                return tree;
            }
            tree = data > tree.data ? tree.right : tree.left;
        }
        return null;
    }

    public BinarySearchTree depthFirstSearch(Integer data) {
        if (Objects.equals(this.data, data)) {
            return this;
        }

        if (left != null) {
            BinarySearchTree leftResult = left.depthFirstSearch(data);
            if (leftResult != null) {
                return leftResult;
            }
        }

        if (right != null) {
            BinarySearchTree rightResult = right.depthFirstSearch(data);
            if (rightResult != null) {
                return rightResult;
            }
        }

        return null;
    }

    public BinarySearchTree breadthFirstSearch(Integer data) {
        Queue<BinarySearchTree> queue = new LinkedList<>();
        queue.add(this);

        while (!queue.isEmpty()) {
            BinarySearchTree tree = queue.poll();
            if (Objects.equals(tree.data, data)) {
                return tree;
            }

            if (tree.left != null) {
                queue.add(tree.left);
            }

            if (tree.right != null) {
                queue.add(tree.right);
            }
        }
        return null;
    }

    public boolean isBalanced() {
        try {
            return isBalanced(this);
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public String toString() {
        return "" + data;
    }
}
