package com.example.mybatisdemo.dto.datasource.table;

import com.example.mybatisdemo.dto.BasePageDTO;
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
public class SysTableInfoQueryDTO extends BasePageDTO {

    @ApiModelProperty("数据源名称")
    @NotBlank(message = "数据源名称 不能为空")
    private String dataSource;

    @ApiModelProperty("数据库名称")
    @NotBlank(message = "数据库名称 不能为空")
    private String tableSchema;


}
