package com.github.maxwell.base;

import org.junit.Test;

/*@RunWith(SpringRunner.class)
@SpringBootTest(classes=TempTest.class)
@ComponentScan(basePackages={"com.gitee.myclouds"})*/

/**
 * 链表实现添加、删除、查询
 */
public class LinkNodeMainTest {

    Node head = new Node();

    @Test
    public void testData() {
        addData(1);
        addData(3);
        addData(5);
        addData(7);
        addData(9);
        addData(10);
        addData(12);
        traveseNode(head);
        insertData(head, 2,20);
        traveseNode(head);
        removeData(head, 2);
        traveseNode(head);
    }

    /**
     * 添加数据
     * @param data
     */
    private void addData(int data){
        //初始化新加入的节点
        Node newNode = new Node(data);

        //临时节点
        Node temp = head;

        //遍历到尾节点
        while (temp.next != null){
            temp = temp.next;
        }

        temp.next = newNode;
    }

    /**
     * 遍历链表数据
     * @param head
     */
    private void traveseNode(Node head){
        //临时节点
        Node temp = head.next;
        while (temp != null){
            System.out.printf("%s ", temp.data);
            temp = temp.next;
        }
        System.out.printf("%n");
    }

    /**
     * 插入节点
     * @param head
     * @param index
     * @param data
     */
    private void insertData(Node head, int index, int data){
        if(index < 1 || index > size(head) + 1){
            System.out.printf("插入位置不合法");
            return;
        }
        //临时Node
        Node temp = head.next;
        //当前Node索引
        int currIndex = 0;
        Node inserNode = new Node(data);
        while (temp != null){
            if((index - 1) == currIndex){
                //temp表示上一个节点
                //temp节点的指向交给新加入的节点
                inserNode.next = temp.next;
                temp.next = inserNode;
                return;
            }
            currIndex ++;
            temp = temp.next;
        }
    }

    /**
     * 删除Node
     * @param head
     * @param index
     */
    private void removeData(Node head, int index){
        if(index < 1 || index > size(head) + 1){
            System.out.printf("删除位置不合法");
            return;
        }
        //临时Node
        Node temp = head.next;
        //当前Node索引
        int currIndex = 0;
        while (temp != null){
            //找到上一个节点位置
            if((index - 1) == currIndex){
                //temp表示上一个节点
                //temp.next表示要删除的节点
                Node removeNode = temp.next;
                //temp.next位置由下一节点指向
                temp.next = removeNode.next;
                removeNode = null;
                return;
            }
            currIndex ++;
            temp = temp.next;
        }
    }

    /**
     * 返回链表长度
     * @param head
     * @return
     */
    private int size(Node head){
        int size = 0;
        //临时节点
        Node temp = head.next;
        while (temp != null){
            size ++ ;
            temp = temp.next;
        }
        return size;
    }

    class Node {
        private int data;// 数据
        private Node next;// 指针

        public Node(){
        }

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
