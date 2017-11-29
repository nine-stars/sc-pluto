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

import java.util.List;

/**
 * Created by zhangshukang on 2017/11/9.
 */

@RestController
@Slf4j
@RequestMapping(value = "/permission")
public class ContentsController {

    @Autowired
    ContentsService sysPermissionService;

    @Autowired
    ContentsFeign1 contentsFeign1;

    @Autowired
    ContentsFeign2 contentsFeign2;


    @RequestMapping(method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "contentFallback")
    public List<Contents> getContents() {
        return contentsFeign1.getContents();
    }


    @RequestMapping(value="/test2",method = RequestMethod.GET)
    public List<Contents> test2() {
        return contentsFeign2.getContents();
    }

    public List<Contents> contentFallback() {
        System.out.println("==============>>>>");
        return null;
    }

}
