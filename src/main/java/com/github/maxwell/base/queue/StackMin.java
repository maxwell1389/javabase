package com.github.maxwell.base.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.Stack;

/**
 * Stack实现min函数
 */
@Slf4j
public class StackMin {
    public StackMin() {
    }

    private Stack<Integer> dataStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void push(int node) {
        dataStack.push(node);
        minStack.push(minStack.isEmpty() ? node : Math.min(minStack.peek(), node));
    }

    public void pop() throws Exception {
        dataStack.pop();
        minStack.pop();
    }

    public int top() {
        return dataStack.peek();
    }

    public int min() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        StackMin stackMin = new StackMin();
        stackMin.push(4);
        stackMin.push(1);
        stackMin.push(6);
        stackMin.push(2);
        try {
            stackMin.pop();
            log.info("top {} min {}", stackMin.top(), stackMin.min());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
