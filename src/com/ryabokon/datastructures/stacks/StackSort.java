package com.ryabokon.datastructures.stacks;

import org.junit.Assert;
import org.junit.Test;

/**
 * Write a program to sort a stack in ascending order (with biggest items on top).
 * You may use at most one additional stack to hold items,
 * but you may not copy the elements into any other data structure (such as an array).
 * The stack supports the following operations: push, pop, peek, and isEmpty.
 * -----------------------------------------------------------------------------------
 *
 * With one additional stack, we can maintain sorted order in that temporary stack.
 * 1) If temp stack has one element it's sorted
 * 2) If we are going to put a new element it it, store this element in a tmp var.
 *    Then find the right place for the new element in a temp stack by
 *    ejecting elements from the temp stack to the main stack.
 *    Put new value in the temp stack.
 *    And put all elements back from the main stack, while sorted order is maintained.
 *
 * That will be O(n^2)
 * ---------------
 * If we have unlimited stacks available, merge sort can be used.
 * The trick is to remember the divided and merged stacks sorting order.
 * Some merge results will have right order: 123; 045;
 * And others will have left order: 321; 540;
 * They need to be merged accordingly.
 *
 */
public class StackSort {

    public Stack<Integer> sortStackInPlace(Stack<Integer> stack) {
        Stack<Integer> tempStack = new StackOnArray<>();
        Integer tempVar;

        tempStack.push(stack.pop());

        while (!stack.isEmpty()) {
            tempVar = stack.pop();

            while (!tempStack.isEmpty() && tempStack.peek() > tempVar) {
                stack.push(tempStack.pop());
            }

            tempStack.push(tempVar);

            while (!stack.isEmpty() && stack.peek() > tempStack.peek()) {
                tempStack.push(stack.pop());
            }
        }

        return tempStack;
    }

    private Stack<Integer> mergeSortStack(Stack<Integer> stack, boolean direction) {

        Stack<Integer> left = new StackOnArray<>();
        Stack<Integer> right = new StackOnArray<>();

        int stackSize = 0;
        while (!stack.isEmpty()) {
            left.push(stack.pop());
            if (!stack.isEmpty()) {
                right.push(stack.pop());
            }
            stackSize++;
        }

        if (stackSize != 1) {
            left = mergeSortStack(left, !direction);
            right = mergeSortStack(right, !direction);
        }

        return merge(left, right, !direction);
    }


    private Stack<Integer> merge(Stack<Integer> left, Stack<Integer> right, boolean direction) {

        Stack<Integer> result = new StackOnArray<>();

        while (!left.isEmpty() && !right.isEmpty()) {
            if (direction ^ (left.peek() < right.peek())) {
                result.push(left.pop());
            } else {
                result.push(right.pop());
            }
        }

        while (!left.isEmpty()) {
            result.push(left.pop());
        }

        while (!right.isEmpty()) {
            result.push(right.pop());
        }

        return result;
    }

    @Test
    public void stackInPlaceSortTest() {
        Stack<Integer> stack = new StackOnArray<>();
        stack.push(5);
        stack.push(10);
        stack.push(8);
        stack.push(1);
        stack.push(3);

        stack = sortStackInPlace(stack);

        Assert.assertTrue(1 == stack.pop());
        Assert.assertTrue(3 == stack.pop());
        Assert.assertTrue(5 == stack.pop());
        Assert.assertTrue(8 == stack.pop());
        Assert.assertTrue(10 == stack.pop());
    }

    @Test
    public void mergeSortTest() {
        Stack<Integer> stack = new StackOnArray<>();
        stack.push(5);
        stack.push(10);
        stack.push(8);
        stack.push(1);
        stack.push(3);

        stack = mergeSortStack(stack, false);

        Assert.assertTrue(1 == stack.pop());
        Assert.assertTrue(3 == stack.pop());
        Assert.assertTrue(5 == stack.pop());
        Assert.assertTrue(8 == stack.pop());
        Assert.assertTrue(10 == stack.pop());
    }
}
