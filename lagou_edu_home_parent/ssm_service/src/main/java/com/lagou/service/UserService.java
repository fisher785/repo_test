package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;

import java.util.List;

public interface UserService {

    /*
        用户分页&多条件组合查询
     */
    PageInfo<User> findAllUserByPage(UserVo userVo);

    /*
        修改用户状态
     */
    void updateUserStatus(Integer id,String status);

    /*
        用户登录
     */
    User login(User user) throws Exception;

    /*
        分配角色（回显）
     */
    List<Role> findUserRelationRoleById(Integer id);

    /*
        用户关联角色
     */
    void userContextRole(UserVo userVo);

    /*
        获取用户权限，进行菜单动态展示
     */
    ResponseResult getUserPermissions(Integer userId);
}
