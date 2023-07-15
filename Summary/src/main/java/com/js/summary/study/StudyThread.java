package com.js.summary.study;

import org.bouncycastle.asn1.x509.qualified.RFC3739QCObjectIdentifiers;

import java.util.ArrayList;
import java.util.Arrays;
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
        String s =
                "23";
        Solution solution = new Solution();
        for (String letterCombination : solution.letterCombinations(s)) {
            System.out.println(letterCombination);
        }
    }
    String[] m = {"","","abc","def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    List<String> res = new ArrayList<>();
    StringBuilder path = new StringBuilder();
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0)return res;
        List<String> digitList = new ArrayList<>();
        for (char c : digits.toCharArray()) {
            digitList.add(m[Integer.parseInt(String.valueOf(c))]);
        }
        re(digitList, 0,  path);
        return res;
    }
    public void re(List<String> digitList , int index, StringBuilder path){
        if (path.toString().length() == digitList.size()){
            res.add(path.toString());
            return;
        }
        for (int i = 0; i < digitList.get(index).length(); i++) {
            char s = digitList.get(index).charAt(i);
            path.append(s);
            re(digitList, index +1, path);
            path.delete(path.length() -1, path.length());
        }
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

    static int sum = 0;
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