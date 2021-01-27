package com.lagou.service.impl;

import com.lagou.dao.PromotionSpaceMapper;
import com.lagou.domain.PromotionSpace;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PromotionSpaceServiceImpl implements PromotionSpaceService {

    @Autowired
    private PromotionSpaceMapper mapper;

    // 查询所有 广告位
    @Override
    public List<PromotionSpace> findAllPromotionSpace() {
        return mapper.findAllPromotionSpace();
    }

    // 新增广告位
    @Override
    public void savePromotionSpace(PromotionSpace promotionSpace) {
        promotionSpace.setSpaceKey(UUID.randomUUID().toString());
        Date date = new Date();
        promotionSpace.setCreateTime(date);
        promotionSpace.setUpdateTime(date);
        promotionSpace.setIsDel(0);

        mapper.savePromotionSpace(promotionSpace);
    }

    // 根据id查询广告位
    @Override
    public PromotionSpace findPromotionSpaceById(Integer id) {
        return mapper.findPromotionSpaceById(id);
    }

    // 更新广告位
    @Override
    public void updatePromotionSpace(PromotionSpace promotionSpace) {
        promotionSpace.setUpdateTime(new Date());
        mapper.updatePromotionSpace(promotionSpace);
    }
}
