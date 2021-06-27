package com.kai.kuangyun.mapper;


import com.kai.kuangyun.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User selectOne(@Param("username") String username);

    void insertUser(@Param("username") String username,@Param("password") String password);
}
