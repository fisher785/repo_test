package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;

import java.util.List;

public interface ResourceService {


    /*
        查询所有资源分类（条件查询）
     */
    PageInfo<Resource> findAllResourceByPage(ResourceVo resourceVo);
}
