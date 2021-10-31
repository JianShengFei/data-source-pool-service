package com.example.mybatisdemo.dto.datasource.table;

import com.example.mybatisdemo.dto.BaseShardingDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author jianshengfei
 * @version 1.0.0
 * @ClassName SysTableInfoQueryDTO.java
 * @Description 系统表查询参数对象
 * @createTime 2021年10月31日 17:47
 */
@Data
public class SysTableInfoQueryDTO extends BaseShardingDTO {

    @ApiModelProperty("页数大小")
    @NotNull(message = "页数大小 不能为空")
    private Integer pageSize;

    @ApiModelProperty("页码")
    @NotNull(message = "页码 不能为空")
    private Integer pageNum;

    @ApiModelProperty("数据库名称")
    @NotBlank(message = "数据库名称 不能为空")
    private String tableSchema;


}
