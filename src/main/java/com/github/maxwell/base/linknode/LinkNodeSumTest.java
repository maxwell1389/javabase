package com.github.maxwell.base.linknode;

/*@RunWith(SpringRunner.class)
@SpringBootTest(classes=TempTest.class)
@ComponentScan(basePackages={"com.gitee.myclouds"})*/

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * 链表求和
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 */
@Slf4j
public class LinkNodeSumTest {

    @Test
    public void test1() {
        Node node1 = readyNode();
        Node node2 = readyNode1();
        Node result = addTwoNum(node1, node2);
        log.info("val {}", toString(result));
    }

    public Node addTwoNum(Node node1, Node node2) {
        Stack<Integer> stack1 = buildStack(node1);
        Stack<Integer> stack2 = buildStack(node2);
        Node head = new Node(-1);
        int carry = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
            int x = stack1.isEmpty() ? 0 : stack1.pop();
            int y = stack2.isEmpty() ? 0 : stack2.pop();
            int sum = x + y + carry;
            Node node = new Node(sum % 10);
            node.next = head.next;
            head.next = node;
            carry = sum / 10;
        }
        return head.next;
    }

    private Stack<Integer> buildStack(Node node) {
        Stack<Integer> stack = new Stack<>();
        while (null != node) {
            stack.push(node.data);
            node = node.next;
        }
        return stack;
    }

    public String toString(Node phead) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        Node curr = phead;
        while (curr != null) {
            builder.append(curr.data);
            curr = curr.next;
            if (curr != null) {
                builder.append(",");
            }

        }
        builder.append("]");
        return builder.toString();
    }

    Node readyNode() {
        Node linkNode1 = new Node(7);
        Node linkNode2 = new Node(2);
        Node linkNode3 = new Node(4);
        Node linkNode4 = new Node(3);
        linkNode1.next = linkNode2;
        linkNode2.next = linkNode3;
        linkNode3.next = linkNode4;
        return linkNode1;
    }

    Node readyNode1() {
        Node linkNode1 = new Node(5);
        Node linkNode2 = new Node(6);
        Node linkNode3 = new Node(4);
        linkNode1.next = linkNode2;
        linkNode2.next = linkNode3;
        return linkNode1;
    }

    class Node {
        private int data;// 数据域
        private Node next;// 指针域

        public Node(int data) {
            // super();
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }


}
