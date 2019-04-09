package com.github.maxwell.base.linknode;

/**
 * 链表逆转
 *
 * @param <T>
 */
public class ReverseLinkNode<T> {

    Node<T> head;
    Node<T> tail;

    public ReverseLinkNode(T... values) {
        for (T value : values) {
            if (tail == null) {
                head = tail = newNode(value);
            } else {
                Node<T> oldTail = tail;
                oldTail.next = tail = newNode(value);
            }
        }
    }

    public ReverseLinkNode<T> reverseByRecursive() {
        Node<T> oldTail = tail;
        tail = reverseFrom(head);
        System.err.println("tail:" + tail);
        System.err.println("head:" + head);
        tail.next = null;
        head = oldTail;
        return this;
    }

    private Node<T> reverseFrom(Node<T> from) {
        if (from == tail)
            return from;
        System.out.print("from:" + from + ",");
        Node<T> end = reverseFrom(from.next);
        System.out.print("end:" + end + ",");
        end.next = from;
        System.out.print("fm:" + from + ",");
        System.out.println("\n");
        return from;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        Node<T> curr = head;
        while (curr != null) {
            builder.append(curr.value);
            curr = curr.next;
            if (curr != null) {
                builder.append(",");
            }

        }
        builder.append("]");
        return builder.toString();
    }

    static class Node<T> {
        T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value.toString();
        }
    }

    private static <T> Node<T> newNode(T value) {
        return new Node<>(value);
    }

    public static void main(String[] args) {
        ReverseLinkNode<Integer> rl = new ReverseLinkNode<>(1, 2, 3, 4, 5, 6);
        System.out.println(rl.reverseByRecursive());
    }

}
