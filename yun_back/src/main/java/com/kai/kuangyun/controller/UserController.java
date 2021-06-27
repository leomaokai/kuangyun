package com.kai.kuangyun.controller;


import com.kai.kuangyun.pojo.UserLoginParam;
import com.kai.kuangyun.service.IUserService;
import com.kai.kuangyun.utils.RespBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Resource
    private IUserService userService;


    @ApiOperation(value = "登录并返回token")
    @PostMapping("/login")
    public RespBean userLogin(@RequestBody UserLoginParam user){
        return userService.login(user.getUsername(),user.getPassword());
    }

//    @ApiOperation(value = "注册")
//    @PostMapping("/register")
//    public RespBean userRegister(@RequestBody UserLoginParam user){
//        return userService.register(user.getUsername(),user.getPassword());
//    }
}
