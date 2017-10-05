package com.ryabokon.datastructures.stacks;

import org.junit.Assert;
import org.junit.Test;

public class StackSort {

    public void sortStack(Stack<Integer> stack) {
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

        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
    }

    @Test
    public void stackInPlaceSortTest() {
        Stack<Integer> stack = new StackOnArray<>();
        stack.push(5);
        stack.push(10);
        stack.push(8);
        stack.push(1);
        stack.push(3);

        sortStack(stack);

        Assert.assertTrue(1 == stack.pop());
        Assert.assertTrue(3 == stack.pop());
        Assert.assertTrue(5 == stack.pop());
        Assert.assertTrue(8 == stack.pop());
        Assert.assertTrue(10 == stack.pop());
    }
}
