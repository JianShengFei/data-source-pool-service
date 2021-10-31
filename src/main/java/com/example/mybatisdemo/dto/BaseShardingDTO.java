package com.example.mybatisdemo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author jianshengfei
 * @version 1.0.0
 * @ClassName BaseShardingDTO.java
 * @Description 动态数据源
 * @createTime 2021年10月31日 17:47
 */
@Data
public class BaseShardingDTO implements Serializable {
    private static final long serialVersionUID = 4382002274971788701L;

    /**
     * 使用的数据源
     */
    @ApiModelProperty(hidden = true)
    private String dataSource;

}
