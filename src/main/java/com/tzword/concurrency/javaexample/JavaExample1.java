package com.tzword.concurrency.javaexample;

import java.util.Stack;

/**
 * 利用栈实现倒序输出
 */
public class JavaExample1 {
    public static void main(String[] args) {
        Stack stack = new Stack();
        String str = "a,b,c,s,d,r,e";
        StringBuffer out = new StringBuffer();
        String[] split = str.split(",");
        for (String s : split) {
            stack.push(s);
        }
        while(!stack.empty()){
            System.out.println(stack.peek());
            out.append(stack.pop());
        }
        System.out.println(out.toString());
    }
}
