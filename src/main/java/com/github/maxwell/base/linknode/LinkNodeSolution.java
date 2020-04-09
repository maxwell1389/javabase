package com.github.maxwell.base.linknode;

import lombok.extern.slf4j.Slf4j;

/**
 * 链表应用
 */
@Slf4j
public class LinkNodeSolution {

    /**
     * 链表中倒数第k个节点
     * <p>
     * 首先两个节点/指针，一个节点 node1 先开始跑，指针 node1 跑到 k-1 个节点后，另一个节点 node2 开始跑，当 node1 跑到最后时，
     * node2 所指的节点就是倒数第k个节点也就是正数第(L-K+1)个节点。
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode FindKthToTail(ListNode head, int k) {
        // 如果链表为空或者k小于等于0
        if (head == null || k <= 0) {
            return null;
        }
        // 声明两个指向头结点的节点
        ListNode node1 = head, node2 = head;
        // 记录节点的个数
        int count = 0;
        // 记录k值，后面要使用
        int index = k;
        // p指针先跑，并且记录节点数，当node1节点跑了k-1个节点后，node2节点开始跑，
        // 当node1节点跑到最后时，node2节点所指的节点就是倒数第k个节点
        while (node1 != null) {
            node1 = node1.next;
            count++;
            if (k < 1) {
                node2 = node2.next;
            }
            k--;
        }
        // 如果节点个数小于所求的倒数第k个节点，则返回空
        if (count < index)
            return null;
        return node2;
    }

    /**
     * 删除链表的倒数第N个节点
     * <p>
     * 简化成另一个问题：删除从列表开头数起的第 (L - n + 1)个结点，其中 L是列表的长度。只要我们找到列表的长度 L，这个问题就很容易解决。
     * <p>
     * 在第一次遍历中，我们找出列表的长度 L。然后设置一个指向哑结点的指针，并移动它遍历列表，直至它到达第 (L - n) 个结点那里。
     * 我们把第 (L - n)个结点的 next 指针重新链接至第 (L - n + 2)个结点，完成这个算法。
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 哑结点，哑结点用来简化某些极端情况，例如列表中只含有一个结点，或需要删除列表的头部
        ListNode dummy = new ListNode(0);
        // 哑结点指向头结点
        dummy.next = head;
        // 保存链表长度
        int length = 0;
        ListNode len = head;
        while (len != null) {
            length++;
            len = len.next;
        }
        length = length - n;
        ListNode target = dummy;
        // 找到 L-n 位置的节点
        while (length > 0) {
            target = target.next;
            length--;
        }
        // 把第 (L - n)个结点的 next 指针重新链接至第 (L - n + 2)个结点
        target.next = target.next.next;
        return dummy.next;
    }

    /**
     * 删除链表的倒数第N个节点
     * <p>
     * 一次遍历法:链表中倒数第N个节点也就是正数第(L-N+1)个节点。
     * <p>
     * 基本思路就是： 定义两个节点 node1、node2;node1 节点先跑，node1节点 跑到第 n+1 个节点的时候,node2 节点开始跑.
     * 当node1 节点跑到最后一个节点时，node2 节点所在的位置就是第 （L-n ） 个节点（L代表总链表长度，也就是倒数第 n+1 个节点）
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // 声明两个指向头结点的节点
        ListNode node1 = dummy, node2 = dummy;
        // node1 节点先跑，node1节点 跑到第 n 个节点的时候,node2 节点开始跑
        // 当node1 节点跑到最后一个节点时，node2 节点所在的位置就是第 （L-n ） 个节点，也就是倒数第 n+1（L代表总链表长度）
        while (node1 != null) {
            node1 = node1.next;
            if (n < 1 && node1 != null) {
                node2 = node2.next;
            }
            n--;
        }
        node2.next = node2.next.next;
        return dummy.next;
    }

    /**
     * 合并两个排序的链表
     *
     * 假设我们有两个链表 A,B；
     * A的头节点A1的值与B的头结点B1的值比较，假设A1小，则A1为头节点；
     * A2再和B1比较，假设B1小,则，A1指向B1；
     * A2再和B2比较 就这样循环往复就行了，应该还算好理解
     *
     * @param list1
     * @param list2
     * @return
     */
    public ReverseLinkNode.ListNode Merge(ReverseLinkNode.ListNode list1, ReverseLinkNode.ListNode list2) {
        if(list1 == null){
            return list2;
        }
        if(list2 == null){
            return list1;
        }
        if(list1.val <= list2.val){
            list1.next = Merge(list1.next, list2);
            return list1;
        }else{
            list2.next = Merge(list1, list2.next);
            return list2;
        }
    }

    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

}
