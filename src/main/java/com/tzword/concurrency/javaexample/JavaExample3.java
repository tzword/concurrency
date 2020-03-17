package com.tzword.concurrency.javaexample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by tzwor on 2020/3/17.
 */
public class JavaExample3 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("aaa");
        list.add("ccc");
        System.out.println("排序前"+list);
        Collections.sort(list);
        System.out.println("排序后"+list);
    }
}
