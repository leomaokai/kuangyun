package com.kai.kuangyun.pojo;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class KFile {

    @ApiModelProperty(value = "文件id")
    private Integer id;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "文件夹类型,1音频,2视频,3照片")
    private Integer type;
    @ApiModelProperty(value = "url")
    private String url;
    @ApiModelProperty(value = "文件名")
    private String filename;
}
