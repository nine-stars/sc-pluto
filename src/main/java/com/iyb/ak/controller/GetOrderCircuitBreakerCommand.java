package com.iyb.ak.controller;

import com.netflix.hystrix.*;

import java.util.Random;

/**
 * Created by zhangshukang on 2017/11/16.
 */
public class GetOrderCircuitBreakerCommand extends HystrixCommand<String> {
    public GetOrderCircuitBreakerCommand(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ThreadPoolTestGroup"))
//                现该参数 了展现该参数 为了展现该参数 分比超过5X
                .andCommandKey(HystrixCommandKey.Factory.asKey("testCommandKey"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey(name))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter().withCircuitBreakerEnabled(true) //默认是true，本例中为了展
                                .withCircuitBreakerForceOpen(false)//默认是false，本例中为
                                .withCircuitBreakerForceClosed(false)//默认是false，本例中
                                .withCircuitBreakerErrorThresholdPercentage(5)//(l)错误半分率超过5%
                                .withCircuitBreakerRequestVolumeThreshold(10) //(2〉
//                                                10s以内调用次数10次，同时满足熔断器打开
                                .withCircuitBreakerSleepWindowInMilliseconds(5000)
//                                                5秒之后，熔断器会尝试半开（关闭”重新放进来请求
                                .withExecutionTimeoutInMilliseconds(1000)
                )
                .andThreadPoolPropertiesDefaults(
                        HystrixThreadPoolProperties.Setter()
                                .withMaxQueueSize(10)   //函置队列大小
                                .withCoreSize(2)));
    } //既置线程池里的线程数

    @Override
    protected String getFallback() {
        return "fallback";
    }


    protected String run() throws Exception {
        Random rand = new Random();
//槿似错误百分比(方式比较粗魯但可以证明问题）
        if (1 == rand.nextInt(2)) {
            throw new Exception("make exception");
        }
        return "running";
    }


    public static void main(String[] args) {
        for(int i=0;i<25;i++) {
            HystrixCommand<String> command = new GetOrderCircuitBreakerCommand("testBreaker");
            String result = command.execute();
            System.out.println("call times:" + (i + 1) + " result:" + result + "isCircuitBreakOpen:" + command.isCircuitBreakerOpen());
        }
    }
}
