package com.example.mybatisdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.example.mybatisdemo.config.DrudConfigProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 【系统数据源连接池】
 * </p>
 *
 * @author jianshengfei
 * @since 2021-10-30
 */
@Getter
@Setter
@TableName("sys_data_source")
@ApiModel(value = "DataScore对象", description = "【系统数据源连接池】")
public class SysDataSource implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("连接池名称")
    private String poolName;

    @ApiModelProperty("JDBC Driver")
    private String driverClassName;

    @ApiModelProperty("JDBC Url")
    private String url;

    @ApiModelProperty("JDBC 用户名")
    private String username;

    @ApiModelProperty("JDBC 密码")
    private String password;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("最后一次更新时间")
    private LocalDateTime lastUpdateTime;

}
