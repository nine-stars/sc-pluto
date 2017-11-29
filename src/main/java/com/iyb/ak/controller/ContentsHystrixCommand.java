package com.iyb.ak.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iyb.ak.entity.Contents;
import com.iyb.ak.feign.ContentsFeign1;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import javax.annotation.Resource;
import java.util.List;

import static org.bouncycastle.asn1.ua.DSTU4145NamedCurves.params;

/**
 * Created by zhangshukang on 2017/11/25.
 */
public class ContentsHystrixCommand extends HystrixObservableCommand<Contents>{


    @lombok.Setter
    @Getter
    private String[] ids;// 模拟前端的批处理，例如需要删除id为1,2,3,4的记录

    @lombok.Setter
    @Getter
    RestTemplate restTemplate;


    public ContentsHystrixCommand(String[] ids,RestTemplate restTemplate) {
        super(HystrixCommandGroupKey.Factory.asKey("usercommand"));// 调用父类构造方法
        this.ids = ids;
        this.restTemplate = restTemplate;
    }

    @Override
    protected Observable<Contents> construct() {
        System.out.println(Thread.currentThread().getName()+"is running......");
        return Observable.create(new Observable.OnSubscribe<Contents>() {
            /*
             * Observable有三个关键的事件方法，分别为onNext，onCompleted，onError，意思很容易懂哈
             */
            @Override
            public void call(Subscriber<? super Contents> observer) {
                try {// 写业务逻辑，注意try-catch
                    if (!observer.isUnsubscribed()) {
                        for (String id : ids) {
                            String url = "http://localhost:8888/content/1";
                            ResponseEntity<Contents> results = restTemplate.exchange(url, HttpMethod.GET, null, Contents.class, params);
                            observer.onNext(results.getBody());
                        }
                        System.out.println("==================>>2");
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io());
    }

    /**
     * fallback方法的写法，覆写resumeWithFallback方法
     * 当调用出现异常时，会调用该降级方法
     */
    @Override
    public Observable<Contents> resumeWithFallback() {
        return Observable.create(new Observable.OnSubscribe<Contents>() {

            @Override
            public void call(Subscriber<? super Contents> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                        Contents contents = new Contents();
                        contents.setContent("admin");
                        observer.onNext(contents);
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io());
    }

}
