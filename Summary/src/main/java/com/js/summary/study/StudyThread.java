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
        int[] arr = {4,4,2,1,4,2,2,1,3};
        Solution solution = new Solution();
        solution.partition("aab");

    }
    List<List<String>> lists = new ArrayList<>();
    Deque<String> deque = new LinkedList<>();

    public List<List<String>> partition(String s) {
        backTracking(s, 0);
        return lists;
    }

    private void backTracking(String s, int startIndex) {
        //如果起始位置大于s的大小，说明找到了一组分割方案
        if (startIndex >= s.length()) {
            lists.add(new ArrayList(deque));
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            //如果是回文子串，则记录
            if (isPalindrome(s, startIndex, i)) {
                String str = s.substring(startIndex, i + 1);
                deque.addLast(str);
            } else {
                continue;
            }
            //起始位置后移，保证不重复
            backTracking(s, i + 1);
            deque.removeLast();
        }
    }
    //判断是否是回文串
    private boolean isPalindrome(String s, int startIndex, int end) {
        for (int i = startIndex, j = end; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
    List<List<String>> res = new ArrayList<>();
    List<String> path = new ArrayList<>();
    StringBuilder sb = new StringBuilder();

    public void reBack(String s, int index){
        if (index >= s.length()){
            res.add(new ArrayList<>(path));
        }
        for (int i = index; i < s.length(); i++) {
            sb.append(s.charAt(i));
            if (check(sb.toString())){
                path.add(sb.toString());
            }else {
                continue;
            }
            reBack(s, i+1);
            sb.deleteCharAt(sb.length() -1);
            path.remove(path.size() -1);
        }
    }
    public boolean check(String s){
        int left = 0;
        int right = s.length() -1;
        while (left <= right){
            if (s.charAt(left) != s.charAt(right))return false;
            left++;
            right--;
        }
        return true;
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