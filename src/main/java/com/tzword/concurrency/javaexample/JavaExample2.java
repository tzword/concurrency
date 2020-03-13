package com.tzword.concurrency.javaexample;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 查找一串字符串中重复的字符的次数
 */
public class JavaExample2 {
    public static void main(String[] args) {
        String str = "abcadgdabdcddd";
        char[] chars = str.toCharArray();
        Set<String> setData = new HashSet<>();
        for (char aChar : chars) {
           setData.add(String.valueOf(aChar));
        }
        //获取没有重复的字符的集合
        System.out.println(setData);
        //计算出相同字符出现的次数
        Map<String,Integer> map = new HashMap<>();
        for (char aChar : chars) {
            String aStr = String.valueOf(aChar);
            if (map.containsKey(aStr)){
                int count = map.get(aStr);
                map.put(aStr,count + 1);
            }else{
                map.put(aStr,1);
            }
        }
        //遍历没有重复的，根据key获取value
        for (String setDatum : setData) {
            System.out.println(setDatum+"出现的次数："+map.get(setDatum));
        }
    }
}
