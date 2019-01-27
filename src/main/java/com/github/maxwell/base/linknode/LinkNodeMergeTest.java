package com.github.maxwell.base.linknode;

import org.junit.Test;

/*@RunWith(SpringRunner.class)
@SpringBootTest(classes=TempTest.class)
@ComponentScan(basePackages={"com.gitee.myclouds"})*/

/**
 * 两有序链表合并
 */
public class LinkNodeMergeTest {

    @Test
    public void test1() {

        ListNode l1 = new ListNode(0);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(5);
        l1.next.next.next = new ListNode(7);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);

        ListNode l = mergeTwoLists(l1, l2);
        while (l != null) {
            System.out.println(l.val);
            l = l.next;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = null;
        result = getResult(result, l1, l2);
        return result;
    }

    /**
     * 递归
     * @param result
     * @param l1
     * @param l2
     * @return
     */
    public ListNode getResult(ListNode result, ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            result = l2;
            //result.next = getResult(result.next, l1, l2.next);
            return result;
        }
        if (l2 == null) {
            result = l1;
            //result.next = getResult(result.next, l1.next, l2);
            return result;
        }
        if (l1.val > l2.val) {
            result = l2;
            l2 = l2.next;
        } else {
            result = l1;
            l1 = l1.next;
        }
        result.next = getResult(result.next, l1, l2);
        return result;
    }

    /**
     * 单向升序链表定义：
     *
     * @author Henry
     * @createDate 2016-10-27
     */
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            this.val = x;
        }
    }
}
