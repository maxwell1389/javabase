package com.github.maxwell.base.algorithm;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;


/**
 * 打印二叉树
 */
@Slf4j
public class PrintTreeNode {

    LinkedList<Integer> printTreeNode(TreeNode root) {
        LinkedList<Integer> datas = Lists.newLinkedList();
        LinkedList<TreeNode> queue = Lists.newLinkedList();
        queue.add(root);
        TreeNode temp = null;
        while (queue.size() != 0) {
            temp = queue.remove(0);
            if (temp.left != null) {
                queue.add(temp.left);
            }
            if (temp.right != null) {
                queue.add(temp.right);
            }
            datas.add(temp.data);
        }
        return datas;
    }

    class TreeNode {
        private int data;// 数据域
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int data) {
            // super();
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }
    }


}
