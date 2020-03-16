package com.github.maxwell.base.algorithm;

import lombok.extern.slf4j.Slf4j;

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

    public int sumRootToLeaf(TreeNode node) {
        return func(node, 0);
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
     * 将当前节点和父节点的值合并并转换成十进制；
     * 遍历整个树，且只有当节点为叶子节点时才做值的统计；
     * <p>
     * 如果是当前节点叶子节点，返回父节点值与当前节点值计算后的值；
     * 如果当前节点是空节点，返回0，以使得结果不受影响；
     * 如果当前节点是中间节点，返回左右子树递归的结果；
     *
     * @param node
     * @param v
     * @return
     */
    private int func(TreeNode node, int v) {
        if (null == node) return 0;
        v = 2 * v + node.value;
        if (null == node.left && null == node.right) return v;
        return func(node.left, v) + func(node.right, v);
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
