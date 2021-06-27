package com.kai.kuangyun.service;

import com.kai.kuangyun.pojo.KFile;
import com.kai.kuangyun.utils.RespBean;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IKFileService {

    List<KFile> listVideo(String name);

    RespBean uploadVideFile(MultipartFile videoFile, String name);

    void downloadVideo(Integer videoId, HttpServletResponse response);

}
