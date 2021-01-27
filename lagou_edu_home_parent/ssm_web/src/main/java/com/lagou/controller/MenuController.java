package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /*
        查询所有菜单
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu() {
        List<Menu> list = menuService.findAllMenu();
        return new ResponseResult(true, 200, "查询所有菜单成功", list);
    }

    /*
        回显菜单信息
     */
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id) {

        List<Menu> menuList = menuService.findSubMenuListByPid(id);
        Map<String, Object> map = new HashMap<>();
        // 根据id判断当前是更新还是添加操作，id是否为-1
        if(id == -1) {
            // 添加操作，回显信息中不需要menu信息
            // 封装数据
            map.put("menuInfo", null);
            map.put("parentMenuList",menuList);
            return new ResponseResult(true, 200, "添加成功", map);
        } else {
            // 修改操作
            Menu menu = menuService.findMenuById(id);
            // 封装数据

            map.put("menuInfo", menu);
            map.put("parentMenuList",menuList);
            return new ResponseResult(true, 200, "修改成功", map);
        }

    }
}
