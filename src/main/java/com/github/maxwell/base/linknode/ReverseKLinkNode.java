package com.github.maxwell.base.linknode;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

/**
 * K个节点之间为一组进行逆序，并且从链表的尾部开始组起
 * 链表:1->2->3->4->5->6->7->8->null,
 * 调整后：1->2->5->4->3->8->7->6->null
 *
 * @param <T>
 */
@Slf4j
public class ReverseKLinkNode<T> {

    Node<T> head;
    Node<T> tail;

    public ReverseKLinkNode(T... values) {
        LinkedList<?> e;
        for (T value : values) {
            if (tail == null) {
                head = tail = newNode(value);
            } else {
                Node<T> oldTail = tail;
                oldTail.next = tail = newNode(value);
            }
        }
    }

    public ReverseKLinkNode<T> reverseByRecursive() {
        Node<T> oldTail = tail;
        tail = reverseFrom(head);
        tail.next = null;
        head = oldTail;
        return this;
    }

    //k个为一组逆序
    public Node<T> reverseKGroup(Node<T> head, int k) {
        Node<T> temp = head;
        for (int i = 1; i < k && temp != null; i++) {
            temp = temp.next;
        }
        //判断节点的数量是否能够凑成一组
        if(temp == null)
            return head;

        Node<T> t2 = temp.next;
        temp.next = null;
        //把当前的组进行逆序
        Node<T> newHead = reverseList(head);
        //把之后的节点进行分组逆序
        Node<T> newTemp = reverseKGroup(t2, k);
        // 把两部分连接起来
        head.next = newTemp;
        this.head = newHead;
        return newHead;
    }

    //逆序单链表
    private Node<T> reverseList(Node<T> phead) {
        if(phead == null || phead.next == null)
            return phead;
        Node<T> result = reverseList(phead.next);
        phead.next.next = phead;
        phead.next = null;
        return result;
    }


    private Node<T> reverseFrom(Node<T> from) {
        if (from == tail)
            return from;
        Node<T> end = reverseFrom(from.next);
        end.next = from;
        return from;
    }
    

    public String toString(Node<T> phead) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        Node<T> curr = phead;
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
        ReverseKLinkNode<Integer> rl = new ReverseKLinkNode<>(1, 2, 3, 4, 5, 6, 7 , 8);
        log.info("v {}", rl.toString(rl.head));
        log.info("v1 {}", rl.reverseByRecursive().toString(rl.head));
        log.info("v2 {}", rl.toString(rl.reverseKGroup(rl.head, 3)));
        log.info("v3 {}", rl.reverseByRecursive().toString(rl.head));
    }
}
