package com.github.maxwell.base.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.Stack;

/**
 * Stack常用的使用案例
 */
@Slf4j
public class StackSolution {

    //判断有效的括号匹配
    private static boolean isValid(String s) {
        Stack<Character> stackChars = new Stack<>();
        char[] chars = s.toCharArray();
        for (char achar : chars) {
            if (stackChars.size() == 0) {
                stackChars.push(achar);
            } else if (isBatchOk(stackChars.peek(), achar)) {
                stackChars.pop();
            } else {
                stackChars.push(achar);
            }
        }
        return stackChars.size() == 0;
    }

    private static boolean isBatchOk(char c1, char c2) {
        return (c1 == '(' && c2 == ')') || (c1 == '[' && c2 == ']') || (c1 == '{' && c2 == '}');
    }



    public static void main(String[] args) {
        String str = "{[()]}";
        log.info("isValid string {}", isValid(str));
    }
}
