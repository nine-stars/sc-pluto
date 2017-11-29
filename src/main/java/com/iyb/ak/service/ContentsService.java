package com.iyb.ak.service;

import com.iyb.ak.entity.Contents;
import com.iyb.ak.mapper.ContentsMapper;
import com.iyb.ak.service.base.BaseService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangshukang on 2017/11/9.
 */

@Service
@Slf4j
public class ContentsService implements BaseService<Contents,String>{

    @Getter
    @Autowired
    ContentsMapper baseMapper;


    public List<Contents> getContents(){
        Contents record = new Contents();
        record.setCid(60);
        return baseMapper.select(record);
    }


    public int updateContent(int id){

        Contents record = baseMapper.selectByPrimaryKey(id);
        record.setSlug("这里是测试");
       return baseMapper.updateByPrimaryKey(record);
    }
}
