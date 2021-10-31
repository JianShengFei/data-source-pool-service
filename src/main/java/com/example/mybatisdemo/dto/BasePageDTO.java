package com.example.mybatisdemo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName BasePageDTO
 * @Description 分页查询基类
 * @Author jianshengfei
 * @Date 2021/10/30 23:33
 */
@Data
public class BasePageDTO {

    @ApiModelProperty("页数大小")
    @NotNull(message = "页数大小 不能为空")
    private Integer pageSize;

    @ApiModelProperty("页码")
    @NotNull(message = "页码 不能为空")
    private Integer pageNum;
}
