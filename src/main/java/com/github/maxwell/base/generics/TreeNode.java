package com.github.maxwell.base.generics;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * 节点树
 */
public class TreeNode<E> implements Serializable {
    E node;
    List<TreeNode<E>> childen;
    boolean isLeaf = true; //是否叶子节点

    public TreeNode(E node) {
        this.node = node;
    }

    public void add(TreeNode<E> node) {
        if (null == childen) childen = new LinkedList<>();
        childen.add(node);
        if (isLeaf) isLeaf = false;
    }
}
