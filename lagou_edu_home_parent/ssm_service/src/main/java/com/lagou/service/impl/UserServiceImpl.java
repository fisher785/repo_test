package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    // 用户分页&多条件组合查询
    @Override
    public PageInfo<User> findAllUserByPage(UserVo userVo) {

        PageHelper.startPage(userVo.getCurrentPage(), userVo.getPageSize());
        List<User> list = userMapper.findAllUserByPage(userVo);

        return new PageInfo<>(list);
    }

    // 修改用户状态
    @Override
    public void updateUserStatus(Integer id, String status) {
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        userMapper.updateUserStatus(user);
    }

    // 用户登录
    @Override
    public User login(User user) throws Exception {

        // 调用Mapper方法
        User userInDatabase = userMapper.login(user);
        if(userInDatabase != null && Md5.verify(user.getPassword(),"lagou",userInDatabase.getPassword())) {
            return userInDatabase;
        } else {
            return null;
        }
    }

    // 分配角色（回显）
    @Override
    public List<Role> findUserRelationRoleById(Integer id) {
        return userMapper.findUserRelationRoleById(id);
    }

    // 关联用户角色
    @Override
    public void userContextRole(UserVo userVo) {
        // 清空中间表关联关系
        userMapper.deleteUserContextRole(userVo.getUserId());

        // 重新建立关联关系
        for (Integer roleId : userVo.getRoleIdList()) {

            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setUserId(userVo.getUserId());
            user_role_relation.setRoleId(roleId);

            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);

            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");

            userMapper.userContextRole(user_role_relation);
        }

    }

    // 获取用户权限信息
    @Override
    public ResponseResult getUserPermissions(Integer userId) {
        // 根据用户id查询用户角色
        List<Role> roleList = userMapper.findUserRelationRoleById(userId);

        // 获取角色id，保存到List中
        List<Integer> roleIds = new ArrayList<>();
        for (Role role : roleList) {
            roleIds.add(role.getId());
        }

        // 3.根据角色id查询父菜单
        List<Menu> parentMenuList = userMapper.findParentMenuByRoleId(roleIds);

        // 4.查询父菜单对应的子菜单
        for (Menu menu : parentMenuList) {
            List<Menu> subMenuList = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(subMenuList);
        }

        // 5.获取用户拥有的资源信息
        List<Resource> resourceList = userMapper.findResourceByRoleId(roleIds);

        // 6.封装数据并放回
        Map<String, Object> map = new HashMap<>();
        map.put("menuList", parentMenuList);
        map.put("resourceList", resourceList);

        return new ResponseResult(true,200,"获取用户权限信息成功", map);
    }
}
