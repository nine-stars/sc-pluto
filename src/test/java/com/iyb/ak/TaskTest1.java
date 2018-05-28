package com.iyb.ak;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by zhangshukang on 2017/11/14.
 */
public class TaskTest1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //初始化线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        List<Future<Integer>> futures = new ArrayList<>();
        for (int j = 1; j <= 5; j++) {

            final int index = j;
            Future<Integer> future = threadPool.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    //第三个线程睡眠等待

                    System.out.println("---------");
                        Thread.sleep(300000l);
                    return index;
                }
            });
            futures.add(future);
        }
        threadPool.shutdown();
        for (Future<Integer> future : futures) {
            System.out.println("线程:"+future.get()+" 任务执行结束:");
        }
    }


}
