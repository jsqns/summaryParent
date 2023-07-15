package com.js.summary.study;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution111 {

    public static void main(String[] args) {
        String[] words = {"cat","banana","dog","nana","walk","walker","dogwalker"};
        System.out.println(longestWord(words));
    }
    public static String longestWord(String[] words){
        int lenMax = 0;
        int left = 0;
        int right = 1;
        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (String word : words) {
            list.add(word.length());
            lenMax = Math.max(lenMax, word.length());
        }

        for (Integer len : list) {
            lenMax = Math.max(lenMax, len);
        }
        List<Integer> listIndex = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == lenMax){
                listIndex.add(i);
            }
        }
        List<Integer> resListIndex = new ArrayList<>();

        for (Integer index : listIndex) {
            String word = words[index];
            while (right < word.length()){
                String sub = word.substring(left, right +1);
                for (String s : words) {
                    if (s.equals(sub)){
                        left = right;
                        if (right == word.length() -1){
                            resListIndex.add(index);
                        }
                        break;
                    }
                }
                right++;
            }
        }

        List<String> res = new ArrayList<>();
        for (Integer index : resListIndex) {
            res.add(words[index]);
        }
        Collections.sort(res);
        if (res.size() >0){
            return res.get(0);
        }else {
            return "";
        }

    }
}
