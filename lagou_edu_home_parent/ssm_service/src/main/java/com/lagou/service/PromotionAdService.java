package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;

public interface PromotionAdService {

    /*
        分页查询广告信息
     */
    PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVo promotionAdVo);

    /*
        新建广告信息
    */
    void savePromotionAd(PromotionAd promotionAd);

    /*
        回显广告信息
    */
    PromotionAd findPromotionAdById(Integer id);

    /*
        修改广告信息
     */
    void updatePromotionAd(PromotionAd promotionAd);


    /*
        广告动态上下线
     */
    void updatePromotionAdStatus(Integer id, Integer status);
}
