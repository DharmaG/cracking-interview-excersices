package com.ryabokon.datastructures.lists;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

/**
 * Given a circular linked list, implement an algorithm which returns the node at the beginning of the loop.
 * DEFINITION
 * Circular linked list: A (corrupt) linked list in which a node's next pointer points to an earlier node, so as to make a loop in the linked list.
 * EXAMPLE
 * I put:A ->B->C->D->E-> C[thesameCasearlier] Output:C
 * <p>
 * ---------------------------------------
 * <p>
 * One approach is to store nodes in a Set while iterating the list, to find if there are any duplicated.
 * It requires O(n) additional memory
 * <p>
 * Other is to use a turtle-rabbit approach with two list runners. One runners moves twice faster than another.
 * And eventually they will meet at the same node.
 */
public class LoopDetector {

    private Node setBasedLoopDetector(Node list) {
        Set<Node> duplicates = new HashSet<>();
        duplicates.add(list);

        while (list.next != null) {
            boolean isDuplicate = !duplicates.add(list.next);
            if (isDuplicate) {
                return list.next;
            }
            list = list.next;
        }
        return list;
    }

    private Node turtleRabbitLoopDetector(Node list) {
        Node turtle = list;
        Node rabbit = list;

        //Finding the meeting point
        while (rabbit != null && rabbit.next != null) {
            turtle = turtle.next;
            rabbit = rabbit.next.next;
            if (turtle == rabbit) {
                break;
            }
        }

        if (rabbit == null || rabbit.next == null) {
            return list;
        }

        //Loop beginning node is equidistant from meeting point and the root node.
        turtle = list;
        while (turtle != rabbit) {
            turtle = turtle.next;
            rabbit = rabbit.next;
        }

        return turtle;
    }


    private void testLoopDetector(Function<Node, Node> detector) {
        Node<String> root = new Node<>("A");
        root.add("B");
        Node<String> loopNode = root.add("C");
        root.add("D");

        Node<String> lastNode = root.add("E");
        lastNode.next = loopNode;

        Node detected = detector.apply(root);
        Assert.assertEquals(loopNode.data, detected.data);
    }

    @Test
    public void testSetLoopDetector() {
        testLoopDetector(this::setBasedLoopDetector);
    }

    @Test
    public void testTurtleRabbitLoopDetector() {
        testLoopDetector(this::turtleRabbitLoopDetector);
    }
}
