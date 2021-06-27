package com.kai.kuangyun.service.impl;

import com.kai.kuangyun.mapper.KFileMapper;
import com.kai.kuangyun.pojo.KFile;
import com.kai.kuangyun.service.IKFileService;
import com.kai.kuangyun.utils.RespBean;
import com.kai.kuangyun.utils.RespBeanEnum;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;


@Service
public class KFileServiceImpl implements IKFileService {

    @Resource
    private KFileMapper fileMapper;
    @Value("${kai.resource}")
    private String resource;

    @Override
    public List<KFile> listVideo(String name) {
        return fileMapper.listVideo(name);
    }

    @Override
    public RespBean uploadVideFile(MultipartFile videoFile, String name) {

        String originalFilename = videoFile.getOriginalFilename();
        String ext = FilenameUtils.getExtension(originalFilename);
        if (!ext.equalsIgnoreCase("mp4")) {
            return RespBean.error(RespBeanEnum.INSERT_ERROR);
        }
        KFile kFile = new KFile();
        String HTTPUrl = "http://leomaokai.xyz:89/" + name + "/" + originalFilename;
        String url = resource + "/" + name + "/" + originalFilename;
        kFile.setFilename(originalFilename).setType(2).setUsername(name).setUrl(HTTPUrl);
        File file = new File(url);
        if (file.exists()) {
            file.delete();
        }
        try (
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file, true));
                BufferedInputStream bufferedInputStream = new BufferedInputStream(videoFile.getInputStream());
        ) {
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = bufferedInputStream.read(bytes)) != -1) {
                bufferedOutputStream.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileMapper.insertOne(kFile);
        return RespBean.success(RespBeanEnum.INSERT_SUCCESS);
    }

    @Override
    public void downloadVideo(Integer videoId, HttpServletResponse response) {
        response.setHeader("content-type", "application/octet-stream");
        KFile kFile = fileMapper.selectOneVideo(videoId);
        System.out.println(kFile.getUrl());
        String fileUrl = kFile.getUrl();
        if (fileUrl == null || fileUrl.equals("")) {
            try {
                response.setHeader("content-disposition", "attachment" + ";filename=" + URLEncoder.encode("", "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return;
        }
        try (
                ServletOutputStream outputStream = response.getOutputStream();
                FileInputStream inputStream = new FileInputStream(fileUrl);
        ) {
            response.setHeader("content-disposition", "attachment" + ";filename=" + URLEncoder.encode(kFile.getFilename(), "UTF-8"));
            IOUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
