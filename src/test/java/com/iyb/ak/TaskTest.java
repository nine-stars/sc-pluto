package com.iyb.ak;

import com.iyb.ak.entity.Contents;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.c;

/**
 * Created by zhangshukang on 2017/11/14.
 */
public class TaskTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Contents a = new Contents();
        a.setCid(1);
        Contents b = new Contents();
//        b.setCid(2);
        Contents c = new Contents();
        c.setCid(3);

        List<Contents> list = new ArrayList();
        list.add(b);
//        list.add(c);
//        list.add(a);

        list.stream().sorted(Comparator.comparing(Contents::getCid)).collect(Collectors.toList());
        list.stream().sorted((x, y) -> x.getCid().compareTo(y.getCid())).collect(Collectors.toList());

        //初始化线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(1);

        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool);
        for (int j = 1; j <= 5; j++) {

            final int index = j;
            completionService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    //第三个线程睡眠等待
                    if (index == 2) {
                        java.lang.Thread.sleep(3000l);
                    }
                    return index;
                }
            });
        }
        threadPool.shutdown();

        for (int i = 0; i < 5; i++) {
            try {
                System.out.println("线程:"+completionService.take().get()+" 任务执行结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }


     /*   for (Future<String> future : futures) {
//            System.out.println(future.get());
        }*/
    }
}
