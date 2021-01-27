package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface UserMapper {

    /*
        用户分页&多条件组合查询
     */
    List<User> findAllUserByPage(UserVo userVo);

    /*
        修改用户状态
     */
    void updateUserStatus(User user);

    /*
        用户登录（根据用户名查询具体的用户信息）
     */
    User login(User user);

    /*
        根据用户id查询关联的角色信息
     */
    List<Role> findUserRelationRoleById(Integer id);

    /*
        根据用户id清空中间表
     */
    void deleteUserContextRole(Integer userId);

    /*
        分配角色
     */
    void userContextRole(User_Role_relation user_role_relation);

    /*
        根据角色id查询角色所拥有的顶级菜单（-1）
     */
    List<Menu> findParentMenuByRoleId(List<Integer> ids);

    /*
        根据父菜单查询子菜单
     */
    List<Menu> findSubMenuByPid(Integer pid);

    /*
        获取用户拥有的资源权限信息
     */
    List<Resource> findResourceByRoleId(List<Integer> ids);

    // 多写了一行注释

}
