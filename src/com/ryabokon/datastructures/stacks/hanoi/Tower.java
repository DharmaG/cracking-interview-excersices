package com.ryabokon.datastructures.stacks.hanoi;

import com.ryabokon.datastructures.stacks.Stack;
import com.ryabokon.datastructures.stacks.StackOnArray;
import com.ryabokon.datastructures.stacks.StackOnList;

public class Tower {
    Stack<String> stack = new StackOnArray<>();
    int itemsCount = 0;


    public void push(String item) {
        stack.push(item);
        itemsCount++;
    }

    public String pop() {
        itemsCount--;
        return stack.pop();
    }

    public int getItemsCount() {
        return itemsCount;
    }
}
