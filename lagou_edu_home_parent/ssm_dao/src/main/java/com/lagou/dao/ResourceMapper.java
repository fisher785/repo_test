package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;

import java.util.List;

public interface ResourceMapper {

    /*
        查询所有资源分类（条件查询）
     */

    List<Resource> findAllResourceByPage(ResourceVo resourceVo);
}
