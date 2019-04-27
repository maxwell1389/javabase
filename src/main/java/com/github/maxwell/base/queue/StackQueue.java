package com.github.maxwell.base.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.Stack;

/**
 * 两个stack实现一个Queue
 */
@Slf4j
public class StackQueue {
    public StackQueue() {
    }

    private Stack<Integer> in = new Stack<>();
    private Stack<Integer> out = new Stack<>();

    public void push(int node) {
        in.push(node);
    }

    public int pop() throws Exception {
        if (out.isEmpty()) while (!in.isEmpty()) out.push(in.pop());
        if (out.isEmpty()) throw new Exception("queue is empty");
        return out.pop();
    }

    public static void main(String[] args) {
        StackQueue stackQueue = new StackQueue();
        stackQueue.push(2);
        stackQueue.push(4);
        try {
            log.info("vla {}", stackQueue.pop());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
