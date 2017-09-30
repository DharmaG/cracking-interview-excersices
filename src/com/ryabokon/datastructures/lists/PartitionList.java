package com.ryabokon.datastructures.lists;

import org.junit.Assert;
import org.junit.Test;

/**
 * Write code to partition a linked list around a value x, such that all
 * Nodes less than x come before all Nodes greater than or equal
 * to x
 * ---------------------------------------------------------------------------
 * Can be done by iterating through the list and saving Nodes to 2
 * another lists: less-than-divider and more-than-divider. And then merging
 * those 2 lists.
 */
public class PartitionList {

    private Node<String> partitionAroundX(Node<String> head, String x) {
        Node<String> less = null;
        Node<String> more = null;
        Node<String> current = head;

        while (current != null) {

            if (x.compareTo(current.data) > 0) {
                less = extractNode(less, current);
            } else if (x.compareTo(current.data) == 0) {
                // If list has duplicate partition values, they should go
                // to the beginning of a "more-than" list.
                Node<String> partition = new Node<>(current.data);
                partition.next = more;
                more = partition;
            } else {
                more = extractNode(more, current);
            }
            current = current.next;
        }

        // Join less and more
        if (less == null) {
            return more;
        } else {
            Node<String> lastOfLess = less;
            while (lastOfLess.next != null) {
                lastOfLess = lastOfLess.next;
            }
            lastOfLess.next = more;
            return less;
        }

    }

    private Node<String> extractNode(Node<String> destination, Node<String> source) {
        if (destination == null) {
            destination = new Node<String>(source.data);
        } else {
            destination.add(source.data);
        }
        return destination;
    }

    @Test
    public void testWithAsserts() {
        Node<String> head = new Node<String>("a");
        head.add("e").add("c").add("b").add("d").add("a").add("c").add("b").add("f");

        Node<String> partitioned = partitionAroundX(head, "c");

        Assert.assertEquals("[a, b, a, b, c, c, e, d, f]", partitioned.toString());
    }
}
