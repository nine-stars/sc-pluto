package com.iyb.ak.controller;

import com.iyb.ak.entity.Contents;
import com.iyb.ak.feign.ContentsFeign1;
import com.iyb.ak.feign.ContentsFeign2;
import com.iyb.ak.service.ContentsService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangshukang on 2017/11/9.
 */

@RestController
@Slf4j
@RequestMapping(value = "/permission2")
public class ContentsController2 {

    @Autowired
    ContentsService sysPermissionService;

    @Autowired
    ContentsFeign1 contentsFeign1;

    @Autowired
    ContentsFeign2 contentsFeign2;

    @Resource
    RestTemplate restTemplate;


    @RequestMapping(method = RequestMethod.GET)
    public List<Contents> getContents() throws InterruptedException {

        List<Contents> result = new ArrayList<>();
        ContentsHystrixCommand command = new ContentsHystrixCommand(new String[]{"2","3"},restTemplate);

        System.out.println("==================>>1");
        Observable<Contents> observe = command.toObservable();
        Thread.sleep(3000l);

        observe.subscribe(new Subscriber<Contents>() {

            @Override
            public void onCompleted() {
                System.out.println("聚合完了所有的查询请求!");
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onNext(Contents content) {
                result.add(content);
                System.out.println(result.size());
            }
        });

        System.out.println("==================>>3");

        return null;
    }


}
