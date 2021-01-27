package com.lagou.service;

import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.domain.Role_menu_relation;

import java.util.List;

public interface RoleService {

    /*
        查询所有角色&条件查询
    */
    List<Role> findAllRole(Role role);

    /*
        根据角色id查询该角色关联的菜单信息id
     */
    List<Integer> findMenuByRoleId(int roleId);

    /*
        为角色分配菜单信息
     */
    void roleContextMenu(RoleMenuVo roleMenuVo);

    /*
        删除角色
     */
    void deleteRole(Integer rid);
}
