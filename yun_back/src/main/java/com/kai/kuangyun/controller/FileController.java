package com.kai.kuangyun.controller;


import com.kai.kuangyun.pojo.KFile;
import com.kai.kuangyun.service.IKFileService;
import com.kai.kuangyun.utils.RespBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/file")
@CrossOrigin
public class FileController {


    @Resource
    private IKFileService fileService;


    @ApiOperation(value = "查询视频")
    @GetMapping("/listVideo")
    public List<KFile> listVideo(Principal principal) {
        return fileService.listVideo(principal.getName());
    }


    @ApiOperation(value = "上传视频")
    @PostMapping("/uploadVideo")
    public RespBean uploadVideo(@RequestParam("videoFile") MultipartFile videoFile, Principal principal) {
        String name = principal.getName();
        return fileService.uploadVideFile(videoFile, name);
    }

    @ApiOperation(value = "下载视频")
    @GetMapping(value = "/downVideo/{videoId}", produces = "application/octet-stream")
    public void downloadVideo(@PathVariable(value = "videoId") Integer videoId, HttpServletResponse response) {
        if (videoId == null) {
            return;
        }
        fileService.downloadVideo(videoId, response);
    }

}
