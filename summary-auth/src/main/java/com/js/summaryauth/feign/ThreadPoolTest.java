package com.js.summaryauth.feign;



import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//@FeignClient
public interface ThreadPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        Semaphore semaphore = new Semaphore(3);

        CountDownLatch countDownLatch = new CountDownLatch(7);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(6, 6, 5, TimeUnit.SECONDS
                , new ArrayBlockingQueue<Runnable>(2), new ThreadPoolExecutor.AbortPolicy());
        try {

            Future<?> future1 = threadPoolExecutor.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
                System.out.println("线程1执行");
                countDownLatch.countDown();
            });
            Object o1 = future1.get();
            Future<?> future2 = threadPoolExecutor.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            throw new RuntimeException(e);
                        }
                        System.out.println("线程2执行");
                        countDownLatch.countDown();
                    }
                    return "1111";
                }
            } );
            Object o = future2.get();
            System.out.println(o.toString());
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
                System.out.println("线程3执行");
                countDownLatch.countDown();
            });
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
                System.out.println("线程4执行");
                countDownLatch.countDown();
            });
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
                System.out.println("线程5执行");
                countDownLatch.countDown();
            });
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
                System.out.println("线程6执行");
                countDownLatch.countDown();
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
            countDownLatch.countDown();
            String s;
        }finally {
            threadPoolExecutor.shutdown();
        }
        countDownLatch.await();
        System.out.println("所有任务处理完成");
    }
}
