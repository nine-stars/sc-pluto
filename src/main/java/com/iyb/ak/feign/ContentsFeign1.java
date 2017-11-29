package com.iyb.ak.feign;

import com.iyb.ak.entity.Contents;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by zhangshukang on 2017/11/10.
 */
@FeignClient(name = "ak")
public interface ContentsFeign1 {

    @RequestMapping(value = "/content",method = RequestMethod.GET)
    List<Contents> getContents();

}
