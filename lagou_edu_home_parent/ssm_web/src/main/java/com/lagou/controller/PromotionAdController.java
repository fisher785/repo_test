package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import com.lagou.utils.UploadPicUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    /*
        广告位分页查询
     */
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(PromotionAdVo promotionAdVo) {
        PageInfo<PromotionAd> pageInfo = promotionAdService.findAllPromotionAdByPage(promotionAdVo);

        return new ResponseResult(true, 200, "分页查询广告位成功", pageInfo);
    }

    /*
        广告位图片上传
    */
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult promotionAdUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        // 1.判断接收到的上传文件是否为空
        if (file.isEmpty()) {
            throw new RuntimeException();
        }

        String path = "ssm-web";

        // 调用工具类，完成map的封装
        Map<String, Object> map = UploadPicUtil.getFileNameAndFilePathMap(file, request, path);

        ResponseResult responseResult = new ResponseResult(true, 200, "图片上传成功", map);

        return responseResult;
    }

    /*
        根据id查找广告信息，回显
     */
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(Integer id) {
        PromotionAd promotionAd = promotionAdService.findPromotionAdById(id);
        return new ResponseResult(true,200,"根据id查询PromotionAd成功", promotionAd);
    }

    /*
        新增或修改广告信息
     */
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setSuccess(true);
        responseResult.setState(200);
        if (promotionAd.getId() == null) {
            promotionAdService.savePromotionAd(promotionAd);
            responseResult.setMessage("保存广告成功");
        } else {
            promotionAdService.updatePromotionAd(promotionAd);
            responseResult.setMessage("修改广告信息成功");
        }
        return responseResult;
    }

    /*
        修改广告上下线状态
     */
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(Integer id,Integer status) {
        promotionAdService.updatePromotionAdStatus(id,status);
        return new ResponseResult(true, 200, "修改状态成功", null);

    }
}
