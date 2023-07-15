package com.js.summary.study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//单词接龙
//字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列 beginWord -> s1 -> s2 -> ... -> sk：
//每一对相邻的单词只差一个字母。
// 对于 1 <= i <= k 时，每个 si 都在 wordList 中。注意， beginWord 不需要在 wordList 中。
//sk == endWord
//给你两个单词 beginWord 和 endWord 和一个字典 wordList ，返回 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0 。
//示例 1：
//输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
//输出：5
//解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
//示例 2：
//输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
//输出：0
//解释：endWord "cog" 不在字典中，所以无法进行转换。
//提示：
//1 <= beginWord.length <= 10
//endWord.length == beginWord.length
//1 <= wordList.length <= 5000
//wordList[i].length == beginWord.length
//beginWord、endWord 和 wordList[i] 由小写英文字母组成
//beginWord != endWord
//wordList 中的所有字符串 互不相同
public class Solution11111 {

    public static void main(String[] args) {
        String beginWord = "hit";
        String  endWord = "cog";
        String[] wordList = {"hot","dot","dog","lot","log","cog"};
        System.out.println(wordChange(beginWord, endWord, wordList));
    }
    static int res = 0;
    static List<Integer> resList = new ArrayList<>();

    static int index = 0;

    static int result = Integer.MAX_VALUE;
    static Set<String> checkSet = new HashSet<>();
    public static int wordChange(String beginWord, String endWord, String[] wordList){
        String[] temp = new String[wordList.length +1];
        temp[0] = beginWord;
        for (int i = 0; i < wordList.length; i++) {
            temp[i +1] = wordList[i];
        }
        String cur = beginWord;
        int result = 0;
        String nextWord = temp[1];
//        rev(beginWord,endWord,wordList, index, 0);
        int resF = Integer.MAX_VALUE;
//        for (Integer res : resList) {
//            resF = Math.min(resF, res);
//        }
        return resF;
    }
    public static void rev(String curWord, String endWord, String[] wordList, int index, int curIndex){
        if (wordCheck(curWord, endWord)){
            result = Math.min(result, res);
            return;
        }
        if (curIndex == wordList.length)return;
        for (int i = curIndex; i < wordList.length; i++) {
            if (wordCheck(curWord, wordList[i])){
                if (checkSet.contains(wordList[i])){
                    continue;
                }
                checkSet.add(wordList[i]);
                res++;
                index++;
                rev(wordList[i], endWord, wordList, index, 0);
                index--;
                res--;
            }else {
                index++;
                rev(curWord, endWord, wordList, index, curIndex +1);
                index--;
            }

        }
    }
    public static boolean wordCheck(String word, String wordCheck){
        int[] check = new int[26];
        Arrays.fill(check, 0);
        for (char c : word.toCharArray()) {
            check[c - 'a'] += 1;
        }
        for (char c : wordCheck.toCharArray()) {
            check[c - 'a'] -=1;
        }
        List<Integer> checkList = new ArrayList<>();
        for (int i : check) {
            if (i != 0){
                checkList.add(i);
            }
        }
        if (checkList.size() == 2){
            boolean b = checkList.get(0) == 1 || checkList.get(1) == -1;
            boolean b2 = checkList.get(0) == -1 || checkList.get(1) == 1;
            return b || b2;
        }
        return false;
    }
}
