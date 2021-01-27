package com.lagou.dao;

import com.lagou.domain.Menu;

import java.util.List;

public interface MenuMapper {

    /*
        查询所有父子菜单信息
     */

    List<Menu> findSubMenuListByPid(int pid);

    /*
        查询所有菜单列表
     */
    List<Menu> findAllMenu();

    /*
        根据id查找menu
     */
    Menu findMenuById(Integer id);
}
