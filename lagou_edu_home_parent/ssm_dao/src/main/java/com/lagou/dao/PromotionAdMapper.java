package com.lagou.dao;

import com.lagou.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {

    /*
        分页查询广告信息
     */
    List<PromotionAd> findAllPromotionAdByPage();

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
    void updatePromotionAdStatus(PromotionAd promotionAd);

}
