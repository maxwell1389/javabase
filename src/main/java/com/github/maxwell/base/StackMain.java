package com.github.maxwell.base;

/*@RunWith(SpringRunner.class)
@SpringBootTest(classes=TempTest.class)
@ComponentScan(basePackages={"com.gitee.myclouds"})*/
public class StackMain {

    //栈顶指针
    private Node head;
    //栈尾指针
    private Node tail;

    //是否为空
    private boolean isEmpty(){
        return head == null;
    }

    //打印所有节点数据
    private void printAll(){
        Node current = head;
        while (current != null){
            System.out.println("current data:" + current.data);
            current = current.next;
        }
    }

    /**
     * 元素入栈
     * @param data
     */
    private void push(int data){
        Node node = new Node(data);
        if(isEmpty()){
            head = node;
            tail = node;
        } else {
            node.next = head;
            head = node;
        }
    }

    /**
     * 出栈
     * @return
     */
    private Node pop(){
        Node current = null;
        if(isEmpty()){
            System.err.println("栈为空");
            return null;
        }
        current = head;
        if(current == tail){
            head = null;
            tail = null;
            System.out.println("当前栈为空");
        } else {
            head = head.next;
        }
        return current;

    }

    public static void main(String[] args) throws InterruptedException {

        StackMain stack = new StackMain();
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        stack.push(0);
        stack.printAll();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.printAll();
    }

    class Node{
        int data;
        Node next;
        public Node(int data){
            this.data = data;
            this.next = null;
        }
    }

}
