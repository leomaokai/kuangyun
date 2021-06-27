package com.kai.kuangyun.service.impl;


import com.kai.kuangyun.config.security.JwtTokenUtil;
import com.kai.kuangyun.mapper.UserMapper;
import com.kai.kuangyun.pojo.User;
import com.kai.kuangyun.service.IUserService;
import com.kai.kuangyun.utils.RespBean;
import com.kai.kuangyun.utils.RespBeanEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.Map;


@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Value("${kai.resource}")
    private String resource;

    @Override
    @Transactional
    public RespBean login(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
            this.register(username, password);
            userDetails = userDetailsService.loadUserByUsername(username);
        }
        if (userDetails != null && !passwordEncoder.matches(password, userDetails.getPassword())) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        // 更新登录对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        System.out.println("authenticationToken==>" + authenticationToken);
        // 得到token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("tokenHead", tokenHead);
        return RespBean.success(RespBeanEnum.LOGIN_SUCCESS, map);
    }


    private RespBean register(String username, String password) {
        if (userMapper.selectOne(username) != null) {
            return RespBean.error(RespBeanEnum.REGISTER_ERROR);
        }
        userMapper.insertUser(username, passwordEncoder.encode(password));
        if (userMapper.selectOne(username) != null) {
            File file = new File(resource + "/" + username);
            if (!file.exists()) {
                file.mkdirs();
            }
            return RespBean.success(RespBeanEnum.REGISTER_SUCCESS);
        }
        return RespBean.error(RespBeanEnum.REGISTER_ERROR);
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.selectOne(username);
    }
}
