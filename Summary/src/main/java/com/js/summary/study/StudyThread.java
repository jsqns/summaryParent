package com.js.summary.study;

import java.util.ArrayList;
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