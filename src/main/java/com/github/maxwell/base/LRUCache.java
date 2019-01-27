package com.github.maxwell.base;

import java.util.HashMap;
import java.util.Map;

/**
 * 最近最少使用缓存
 */
//@Slf4j
public class LRUCache {

    private int capacity;
    private Map<Integer, Node> caches;
    private Node first;
    private Node last;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        caches = new HashMap<Integer, Node>(capacity);
    }

    public void put(int key, int value) {
        //首先判断是否存在
        Node node = caches.get(key);
        if (node == null) {
            //如果不存在，先判断容量
            if (capacity <= caches.size()) {
                //不够，移除最后元素
                removeLast();
            }
            node = new Node();
            node.key = key;
        }
        node.vlaue = value;
        moveNodeToFirst(node);
        caches.put(key, node);
    }

    public int get(int key) {
        Node node = caches.get(key);
        if (node == null)
            return -1;
        moveNodeToFirst(node);
        return node.vlaue;
    }

    private void removeLast() {
        if (last != null) {
            caches.remove(last.key);
            last = last.pre;
            if (last != null) {
                last.next = null;
            } else {
                first = null;
            }
        }
    }

    private void moveNodeToFirst(Node node) {
        if (first == node || node == null)
            return;
        if (node.pre != null) {
            node.pre.next = node.pre;
        }
        if (node == last) {
            last = node.pre;
        }
        if (last == null || first == null) {
            last = first = node;
            return;
        }
        node.next = first;
        first.pre = node;
        first = node;
        node.pre = null;
    }

    class Node {
        Node next;
        Node pre;
        int key;
        int vlaue;
    }
}
