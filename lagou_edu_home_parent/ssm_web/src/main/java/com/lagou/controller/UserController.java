package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /*
        用户分页&多条件组合查询
     */
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo) {
        PageInfo<User> pageInfo = userService.findAllUserByPage(userVo);
        return new ResponseResult(true,200,"分页查询成功", pageInfo);
    }

    /*
        修改用户状态
     */
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(Integer id,String status) {
        userService.updateUserStatus(id,status);
        return new ResponseResult(true, 200, "修改用户状态成功", status);
    }

    /*
        用户登录
     */
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setSuccess(true);
        User user1 = userService.login(user);
        if(user1!=null) {
            // 保存用户id及access_token到session中
            HttpSession session = request.getSession();
            String access_token = UUID.randomUUID().toString();
            session.setAttribute("access_token",access_token);
            session.setAttribute("user_id",user1.getId());

            // 将查询的结果信息响应给前端
            Map<String, Object> map = new HashMap<>();
            map.put("access_token",access_token);
            map.put("user_id",user1.getId());
            // 将查询出来的user也存到map中
            map.put("user", user1);

            responseResult.setState(1);
            responseResult.setMessage("登录成功");
            responseResult.setContent(map);

        } else {
            responseResult.setState(400);
            responseResult.setMessage("用户名或密码错误");
        }
        return responseResult;
    }

    /*
        分配角色（回显）
     */
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRelationRoleById(Integer id){
        List<Role> list = userService.findUserRelationRoleById(id);
        return new ResponseResult(true, 200, "分配角色成功", list);
    }

    /*
        分配角色
     */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo) {
        userService.userContextRole(userVo);

        return new ResponseResult(true, 200, "分配角色成功", null);
    }

    /*
        获取用户权限，进行菜单动态展示
     */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request) {
        // 1.获取请求头中的token
        String header_token = request.getHeader("Authorization");

        // 2.获取session中的token
        String session_token = (String) request.getSession().getAttribute("access_token");

        // 3.判断token是否一致
        if(header_token.equalsIgnoreCase(session_token)) {
            Integer user_id = (Integer) request.getSession().getAttribute("user_id");

            return userService.getUserPermissions(user_id);
        } else {
            return new ResponseResult(false, 400, "获取用户信息失败", null);
        }
    }
}
