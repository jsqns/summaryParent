package com.js.summary.study;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

//姓名：覃铄杰
//手机号： 18677267894
public class Solution1111 {
    public static void main(String[] args) {
        Solution1111 solution1111 = new Solution1111();
        String s =
                "FFFTTFTTFT";
        System.out.println(solution1111.answerW(s, 3));
    }
    public int answerW(String answerKey, int k){
        if (answerKey.length() == 1)return 1;
        int left = 0;
        int right = 1;
        int temp = k;
        List<Integer> res = new ArrayList<>();
        while (right < answerKey.length()){
            if (temp < 0){
                left++;
                right = left +1;
                temp = k;
            }
            if (answerKey.charAt(left) == answerKey.charAt(right)){
                right++;
            }else if (temp > 0){
                temp--;
                right++;
            }else {
                temp--;
            }
            if (temp >= 0){
                res.add(right -left);
            }
        }
        int result = 0;
        for (Integer re : res) {
            result = Math.max(re, result);
        }
        return result;
    }
    public int answer(String answerKey, int k){
        int result = 0;
        int left = 0;
        int right = 1;
        Deque<Character> res = new LinkedList();
        Map<String, Integer> map = new HashMap<>();
        while (right < answerKey.length()){
            if (answerKey.charAt(left) == answerKey.charAt(right)){
                right++;
            }else {
                map.put(right -1 + "&" + left, right - left);
                left = right;
            }
        }
        List<String> list = new ArrayList<>();
        List<Integer> ranList = new ArrayList<>();
        int temp = 0;
        for (String s : map.keySet()) {
            ranList.add(map.get(s));
        }
        int max = 0;
        if (ranList.size() == 1){
            max = ranList.get(0);
        }else {
            Collections.sort(ranList);
            for (int i = ranList.size() -1; i > 0; i--) {
                temp = i -1;
                if (!Objects.equals(ranList.get(i), ranList.get(temp))){
                    max = ranList.get(i);
                }
            }
        }
        for (String s : map.keySet()) {
            if (map.get(s) == max){
                list.add(s);
            }
        }
        result = max;
        for (String s : list) {
            String[] split = s.split("&");
            int r = Integer.parseInt(split[0]);
            int l = Integer.parseInt(split[1]);
            for (int i = r +1; i < answerKey.length(); i++) {
                if (answerKey.charAt(r) != answerKey.charAt(r + 1) && k > 0){
                    k--;
                    result = Math.max(result, i -l +1);
                }else {
                    result += 1;
                }
            }
            if (k > 0){
                if (l > 0){
                    for (int i = l +1; i >0; i--) {
                        if (answerKey.charAt(l) != answerKey.charAt(l - 1) && k > 0){
                            k--;
                            result += 1;
                        }else {
                            result +=1;
                        }
                    }
                }
            }else {
                if (l > 0){
                    for (int i = l +1; i >0; i--) {
                        if (answerKey.charAt(l) != answerKey.charAt(l - 1) && k > 0){
                            k--;
                        }else {
                            result +=1;
                        }
                    }
                }
            }
        }
        return result;
    }
}
