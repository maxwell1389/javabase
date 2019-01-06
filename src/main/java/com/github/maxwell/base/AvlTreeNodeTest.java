package com.github.maxwell.base;

import org.junit.Test;

/*@RunWith(SpringRunner.class)
@SpringBootTest(classes=TempTest.class)
@ComponentScan(basePackages={"com.gitee.myclouds"})*/

/**
 * 平衡二叉树
 */
public class AvlTreeNodeTest {

    boolean isAvl = true; //是否二叉树

    @Test
    public void test1() {

    }

    /**
     * 判断平衡二叉树 通过遍历，计算高度另起方法
     * @param root
     * @return
     */
/*    @Test
    public boolean isAvlTreeNode(TreeNode root){
        if(root == null)
            return true;
        int left = depth(root.left);
        int right = depth(root.right);
        if(Math.abs(left - right) > 1){
            return false;
        }
        boolean bleft = isAvlTreeNode(root.left);
        boolean bright = isAvlTreeNode(root.right);
        return bleft && bright;
    }*/

    /**
     * 判断是否二叉树，后续遍历法
     * @return
     */
    @Test
    public void isAvlTreeAfter(){
        TreeNode root = new TreeNode(1);
        TreeNode cld = new TreeNode(2);
        root.left = cld;
        cld = new TreeNode(3);
        root.right = cld;
        cld = new TreeNode(4);
        root.left.left = cld;
        cld = new TreeNode(5);
        root.left.right = cld;
        cld = new TreeNode(6);
        root.right.left = cld;
        cld = new TreeNode(7);
        root.right.right = cld;
        calDepth(root);
    }

    private int calDepth(TreeNode root){
        if(root == null)
            return  0;
        int left = calDepth(root.left);
        int right = calDepth(root.right);
        if(Math.abs(left - right) > 1){
            isAvl = false;
        }
        return (left > right) ? (left + 1) :(right + 1);
    }

    /**
     * 计算深度
     * @param root
     * @return
     */
    private int depth(TreeNode root){
        if(root == null)
            return 0;
        int left = depth(root.left);
        int right = depth(root.right);
        return (left > right) ?(left + 1):(right + 1);
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
