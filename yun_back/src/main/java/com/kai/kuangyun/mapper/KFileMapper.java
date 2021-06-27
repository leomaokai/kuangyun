package com.kai.kuangyun.mapper;

import com.kai.kuangyun.pojo.KFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KFileMapper {
    List<KFile> listVideo(@Param("name") String name);

    void insertOne(@Param("kFile") KFile kFile);

    KFile selectOneVideo(@Param("videoId") Integer videoId);
}
