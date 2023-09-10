package com.js.summary.study;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Solution22 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String[] split = s.split("\\|");
        if (split.length == 1){
            String substring = s.substring(4);
            System.out.println(substring);
            return;
        }
        String init;
        List<String> res = new ArrayList<>();
        boolean bre = false;
        for (int i = 0; i < split.length; i++) {

            if (i == 0){
                boolean check = check(split[0]);
                if (!check){
                    System.out.println("error");
                    break;
                }
                init = split[0].substring(4);
                res.add(0, init);
            }else {
                String[] s1 = split[i].split(" ");
                if (Integer.parseInt(s1[0]) > res.size()){
                    System.out.println("error");
                    bre = true;
                    break;
                }
                if ("a".equals(s1[1])){
                    res.add(Integer.parseInt(s1[0]) , split[i].substring(4));
                }else if ("i".equals(s1[1])){
                    res.add(Integer.parseInt(s1[0]) -2, split[i].substring(4));
                }else if ("r".equals(s1[1])){
                    res.remove(Integer.parseInt(s1[0]) -1);
                    res.add(Integer.parseInt(s1[0]) -1, split[i].substring(4));
                }else if ("d".equals(s1[1])){
                    res.remove(Integer.parseInt(s1[0]) -1);
                }else {
                    System.out.println("error");
                    bre = true;
                    break;
                }
            }
        }
        if (!bre){
            for (String re : res) {
                System.out.println(re);
            }
        }
    }
    private static boolean check(String s){
        String[] s2 = s.split(" ");
        return Objects.equals(s2[0], "1") && Objects.equals(s2[1], "i");
    }
}
