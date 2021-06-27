package com.kai.kuangyun.service;

import com.kai.kuangyun.pojo.User;
import com.kai.kuangyun.utils.RespBean;

public interface IUserService {

    User getUserByUsername(String username);

    RespBean login(String username, String password);

}
