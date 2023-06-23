package com.js.summary.study;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class StudyAQS {
    public static void main(String[] args) {
        String s = new String("22");
        String s1 = s;
        s = new String("11");
    }
    public void test() throws InterruptedException {
        Semaphore semaphore = new Semaphore(2);
        semaphore.tryAcquire(1, TimeUnit.SECONDS);
        CountDownLatch countDownLatch = new CountDownLatch(4);
        countDownLatch.countDown();
        countDownLatch.await();
    }
}
