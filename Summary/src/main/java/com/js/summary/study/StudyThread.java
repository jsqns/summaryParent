package com.js.summary.study;

import org.bouncycastle.asn1.x509.qualified.RFC3739QCObjectIdentifiers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
class Solution {


    //输入：target = 7, nums = [2,3,1,2,4,3]
    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        ListNode listNode5 = new ListNode(5);
        ListNode listNode4 = new ListNode(4,listNode5);
        ListNode listNode3 = new ListNode(3,listNode4);
        ListNode listNode2 = new ListNode(2,listNode3);
        ListNode listNode1 = new ListNode(1, listNode2);
        int[] arr = {1,2,3};
        Solution solution = new Solution();
        for (List<Integer> list : solution.subsets(arr)) {
            System.out.println(list.toString());
        }

    }
    //子集2
    List<List<Integer>> subsetsWithDupRes = new ArrayList<>();
    List<Integer> subsetsWithDupPath = new ArrayList<>();
    boolean[] subsetsWithDupCheck;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        subsetsWithDupCheck = new boolean[nums.length];
        Arrays.sort(nums);
        Arrays.fill(subsetsWithDupCheck, false);
        subsetsWithDupBack(0, nums);
        return subsetsWithDupRes;
    }
    public void subsetsWithDupBack(int index, int[] nums){
        subsetsWithDupRes.add(new ArrayList<>(subsetsWithDupPath));
        for (int i = index; i < nums.length; i++) {
            if (i >0 && nums[i] == nums[i -1] && !subsetsWithDupCheck[i -1]){
                continue;
            }
            subsetsWithDupCheck[i] = true;
            subsetsWithDupPath.add(nums[i]);
            subsetsWithDupBack(i +1, nums);
            subsetsWithDupCheck[i] = false;
            subsetsWithDupPath.remove(subsetsWithDupPath.size() -1);
        }
    }
    //子集
    List<List<Integer>> subsetsRes = new ArrayList<>();
    List<Integer> subsetsPath = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        subsetsBack(0, nums);
        return subsetsRes;
    }
    public void subsetsBack(int index, int[] nums){
        subsetsRes.add(new ArrayList<>(subsetsPath));
        for (int i = index; i < nums.length; i++) {
            subsetsPath.add(nums[i]);
            subsetsBack(i +1, nums);
            subsetsPath.remove(subsetsPath.size() -1);
        }
    }
    //分割回文子字符串
    List<List<String>> partitionRes = new ArrayList<>();
    List<String> partitionPath = new ArrayList<>();
    public List<List<String>> partition(String s) {
        partitionBack(s, 0);
        return partitionRes;
    }
    public void partitionBack(String s, int index){
        if (index >= s.length()){
            if (!partitionPath.isEmpty()){
                partitionRes.add(new ArrayList<>(partitionPath));
            }
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (partitionCheck(s, index, i)){
                partitionPath.add(s.substring(index, i +1));
            }else {
                continue;
            }
            partitionBack(s, i +1);
            if (!partitionPath.isEmpty()){
                partitionPath.remove(partitionPath.size() -1);
            }
        }

    }
    public boolean partitionCheck(String s, int start, int end){
        while (start < end){
            if (s.charAt(start) != s.charAt(end)){
                return false;
            }
            end--;
            start++;
        }
        return true;
    }

    //组合总和2
    List<List<Integer>> resCombinationSum2 = new ArrayList<>();
    List<Integer> pathCombinationSum2 = new ArrayList<>();
    int sumCombinationSum2 = 0;
    boolean[] used ;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        used = new boolean[candidates.length];
        Arrays.fill(used, false);
        Arrays.sort(candidates);
        combinationSum2Back(candidates, target, 0);
        return resCombinationSum2;
    }
    public void combinationSum2Back(int[] candidates, int target, int index){
        if (sumCombinationSum2 == target){
            resCombinationSum2.add(new ArrayList<>(pathCombinationSum2));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (sumCombinationSum2 + candidates[i] > target){
                break;
            }
            if (i >0 && candidates[i] == candidates[i -1] && !used[i -1]){
                continue;
            }
            sumCombinationSum2 +=  candidates[i];
            pathCombinationSum2.add(candidates[i]);
            used[i] = true;
            combinationSum2Back(candidates, target, i +1);
            used[i] = false;
            sumCombinationSum2 -= candidates[i];
            pathCombinationSum2.remove(pathCombinationSum2.size() -1);
        }
    }

    //组合综合1
    List<List<Integer>> resCombinationSum = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    int sum = 0;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        //升序排列
        Arrays.sort(candidates);
        combinationSumBack(candidates, target, path, 0);
        return resCombinationSum;
    }
    public void combinationSumBack(int[] candidates, int target, List<Integer> path, int index){
        if (sum == target){
            resCombinationSum.add(new ArrayList<>(path));
        }
        for (int i = index; i < candidates.length; i++) {
            if ((sum + candidates[i]) > target){
                break;
            }
            sum += candidates[i];
            path.add(candidates[i]);
            combinationSumBack(candidates, target, path, i);
            sum -= candidates[i];
            path.remove(path.size() -1);
        }
    }

    //考试最大难度
    int ans = 0;
    public int maxConsecutiveAnswers(String answerKey, int k) {
        
        return Math.max(getLen(answerKey, 'T', k), getLen(answerKey, 'F',k));
    }
    public int getLen(String s, char c, int k){
        int resNum = 0;
        for (int i = 0,j = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) ans++;
            while (ans > k){
                if (s.charAt(j) == c)ans--;
                j++;
            }
            resNum = Math.max(resNum, i -j +1);
        }
        return resNum;
    }
    //复原ip地址
    List<String> res = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    public List<String> restoreIpAddresses(String s) {
        sb = new StringBuilder(s);
        reBack(s,0);
        return res;
    }
    public void reBack(String s, int index){
        if (index == 3){
            if (check(s.substring(index))) {
                sb = new StringBuilder(s);
                res.add(sb.toString());
            }
            return;
        }
        for (int i = index; i < 4; i++) {
            if (!check(s.substring(index, i +1))){
                break;
            }
            reBack(s, i+1);
        }
    }
    public boolean check(String s){
        long value = Long.parseLong(s);
        if (value >= 0L && value <= 255L){
            sb.insert(sb.lastIndexOf(".") +s.length()+1, '.');
            return true;
        }
        return false;
    }

    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedList<>();
        for (String token : tokens) {
            if ("+".equals(token)){
                stack.push(stack.pop() + stack.pop());
            }else if ("-".equals(token)){
                stack.push(-stack.pop() + stack.pop());
            }else if ("*".equals(token)){
                stack.push(stack.pop() * stack.pop());
            }else if ("/".equals(token)){
                Integer pop = stack.pop();
                Integer pop1 = stack.pop();
                stack.push(pop1 / pop);
            }else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
    public String removeDuplicates(String s) {
        Deque<Character> stack = new LinkedList<>();
        stack.push(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            if (!stack.isEmpty() && stack.getLast() == s.charAt(i)){
                stack.removeLast();
            }else {
                stack.addLast(s.charAt(i));
            }
        }
        StringBuilder builder = new StringBuilder();
        for (Character character : stack) {
            builder.append(character);
        }
        return builder.toString();
    }
    public boolean isValid(String s) {
        if (s.length() == 1){return false;}
        Stack<String> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            String val = String.valueOf(c);
            if ("(".equals(val)){
                stack.push(")");
            }else if ("[".equals(val)){
                stack.push("]");
            }else if ("{".equals(val)){
                stack.push("}");
            }else if (stack.isEmpty() || !stack.peek().equals(val)){
                return false;
            }else {stack.pop();}
        }
        return stack.isEmpty();
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        return nums;
    }
static class MyQueue{
    Deque<Integer> deque = new LinkedList<>();
    public void poll(int val){
        if (!deque.isEmpty() && deque.peek() == val){
            deque.poll();
        }
    }
    public void add(int val){

    }
}
    public ListNode rm(ListNode cur, ListNode pre, ListNode rm){
        if (cur == null){
            return pre;
        }
        ListNode last = rm(cur.next, pre.next, rm);
        if (rm.val == last.val){
            pre.next = cur.next;
        }
        return pre;
    }

static class ListNode {
    int val;
   ListNode next;
   ListNode() {}
    ListNode(int val) { this.val = val; }
   ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

}
public class StudyThread {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Semaphore semaphore = new Semaphore(2);
        semaphore.acquire();
        semaphore.release();
        semaphore.acquire();
        FutureTask futureTask = new FutureTask<>(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        });
        futureTask.get();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };
        runnable.run();
    }
}
class V{
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
    }
}

class Solution1 {
    public static void main(String[] args) {
        int[] nums = {
                10,9,2,5,3,7,101,18};
        lengthOfLIS(nums);
    }
    public static int lengthOfLIS(int[] nums) {
        //定义dp数组dp[i]表示以nums[i]结尾的最大长度
        int[] dp = new int[nums.length];
        //递推公式dp[i] = max(dp[i],dp[j] +1)
        //初始化
        Arrays.fill(dp,1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j])dp[i] =  Math.max(dp[i],dp[j] +1);
            }
        }
        int res = 1;
        for (int i : dp) {
            res = Math.max(i,res);
        }
        return res;
    }
}
class CallableDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<Future<String>>();
        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
//        concurrentHashMap.putAll();
//        concurrentHashMap.put();
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        Condition condition = reentrantLock.newCondition();
        condition.wait();
        Condition condition1 = reentrantLock.newCondition();
        Condition condition2 = reentrantLock.newCondition();
        for(int i=0;i<10;i++)
            results.add(exec.submit(new TaskWithResult(i)));
        for (Future<String> future : results) {

            try {
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }finally{
                exec.shutdown();
            }

        }
    }
}
class TaskWithResult implements Callable<String>{
    private int id;
    public TaskWithResult(int id){
        this.id = id;
    }
    @Override
    public String call()throws Exception  {
        return "result = "+id;
    }
}