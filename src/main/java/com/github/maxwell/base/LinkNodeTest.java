package com.github.maxwell.base;

/*@RunWith(SpringRunner.class)
@SpringBootTest(classes=TempTest.class)
@ComponentScan(basePackages={"com.gitee.myclouds"})*/

import org.junit.jupiter.api.Test;

/**
 * 单有序链表反转
 */
public class LinkNodeTest {

    @Test
    public void test1() {
        Node oNode = readyNode();
        Node newNode = reverseLinkedList1(oNode);
        System.out.print(newNode);
    }

    /**
     * 遍历方法
     * @param node
     * @return
     */
    Node reverseLinkedList(Node node) {
        Node previousNode = null;
        Node currentNode = node;
        Node headNode = null;
        while (currentNode != null) {
            Node nextNode = currentNode.next;
            if (nextNode == null) {
                headNode = currentNode;
            }
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextNode;
        }
        return headNode;
    }

    /**
     * 递归方法
     * @param node
     * @return
     */
    Node reverseLinkedList1(Node node) {
        if (node == null || node.next == null) {
            return node;
        } else {
            Node headNode = reverseLinkedList1(node.next);
            node.next.next = node;
            node.next = null;
            return headNode;
        }
    }

    Node readyNode() {
        Node linkNode1 = new Node(1);
        Node linkNode2 = new Node(2);
        Node linkNode3 = new Node(3);
        Node linkNode4 = new Node(4);
        Node linkNode5 = new Node(5);
        Node linkNode6 = new Node(6);
        linkNode1.next = linkNode2;
        linkNode2.next = linkNode3;
        linkNode3.next = linkNode4;
        linkNode4.next = linkNode5;
        linkNode5.next = linkNode6;
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
