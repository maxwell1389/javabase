package com.github.maxwell.base.algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 二叉树
 */
@Slf4j
public class BinaryTreeTest {

    //最大深度
    public int maxDeath(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = maxDeath(node.left);
        int right = maxDeath(node.right);
        return Math.max(left, right) + 1;
    }

    //最小深度
    public int minDeath(TreeNode node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        return Math.min(minDeath(node.left), minDeath(node.right)) + 1;
    }

    //是否平衡二叉树
    public boolean isBalance(TreeNode node) {
        return calAvl(node) != -1;
    }


    public void toString(TreeNode root) {
        if (root != null) {
            toString(root.left);
            log.info("value = {} ->", root.value);
            toString(root.right);
        }
    }

    private int calAvl(TreeNode node) {
        if (node == null) return 0;
        int left = calAvl(node.left);
        int right = calAvl(node.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;
    }

    /**
     * 节点
     */
    class TreeNode {

        /**
         * 节点值
         */
        int value;

        /**
         * 左节点
         */
        TreeNode left;

        /**
         * 右节点
         */
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }

}
