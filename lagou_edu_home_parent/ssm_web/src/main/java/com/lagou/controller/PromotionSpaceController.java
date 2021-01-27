package com.lagou.controller;

import com.lagou.domain.PromotionSpace;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/PromotionSpace")
public class PromotionSpaceController {

    @Autowired
    private PromotionSpaceService service;

    /*
        查询广告位列表
     */
    @RequestMapping("/findAllPromotionSpace")
    private ResponseResult findAllPromotionSpace() {
        List<PromotionSpace> list = service.findAllPromotionSpace();
        return new ResponseResult(true,200,"查询所有广告位成功", list);
    }

    /*
        添加广告位
     */
    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionSpace promotionSpace) {

        ResponseResult responseResult = new ResponseResult();
        responseResult.setSuccess(true);
        responseResult.setState(200);

        if(promotionSpace.getId() == null) {
            service.savePromotionSpace(promotionSpace);
            responseResult.setMessage("新增广告位成功");
        } else {
            service.updatePromotionSpace(promotionSpace);
            responseResult.setMessage("更新广告位成功");
        }

        return responseResult;
    }

    /*
        根据id查询广告位
     */
    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(Integer id){
        PromotionSpace promotionSpace = service.findPromotionSpaceById(id);
        return new ResponseResult(true, 200, "查询具体广告位成功", promotionSpace);
    }
}
