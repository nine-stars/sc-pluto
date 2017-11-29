package com.iyb.ak;

import java.util.concurrent.*;

/**
 * Created by zhangshukang on 2017/11/14.
 */
public class TaskTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //初始化线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool);
        for (int j = 1; j <= 10; j++) {

            final int seq = j;
            completionService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {

                    System.out.println("task finished");

                    if (seq == 1) {
//                        java.lang.Thread.sleep(8000l);
                    }

                    return seq;
                }
            });
        }
//        pool.shutdown();

   /*     for (int i = 0; i < 10; i++) {
            try {
                System.out.println(completionService.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }*/


     /*   for (Future<String> future : futures) {
//            System.out.println(future.get());
        }*/
    }
}


class Task implements Callable<String> {

    Integer i;

    public Task(Integer i) {
        this.i = i;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public String call() throws Exception {

    /*    if(i==3){
            Thread.sleep(6000l);
        }*/
        return i+"";
    }
}
