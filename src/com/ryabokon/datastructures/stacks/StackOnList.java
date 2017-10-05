package com.ryabokon.datastructures.stacks;

import com.ryabokon.datastructures.lists.Node;

public class StackOnList<T> implements Stack<T> {

    Node<T> head;

    @Override
    public T pop() {
        if (head == null) {
            return null;
        }

        T data = head.data;
        head = head.next;

        return data;
    }

    @Override
    public T peek() {
        if (head == null) {
            return null;
        }
        return head.data;
    }

    @Override
    public void push(T obj) {
        if (head == null) {
            head = new Node<>(obj);
        } else {
            Node<T> n = new Node<>(obj);
            n.next = head;
            head = n;
        }

    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

}
