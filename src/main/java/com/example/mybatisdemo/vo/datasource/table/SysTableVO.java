package com.example.mybatisdemo.vo.datasource.table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author jianshengfei
 * @version 1.0.0
 * @ClassName SysTableVO.java
 * @Description 系统表视图层对象
 * @createTime 2021年10月31日 18:03
 */
@Data
public class SysTableVO {

    @ApiModelProperty("数据表所属的数据库名")
    private String tableSchema;

    @ApiModelProperty("表名")
    private String tableName;

    @ApiModelProperty("数据库引擎")
    private String engine;

    @ApiModelProperty("表里所存多少行数据")
    private Integer tableRows;

    @ApiModelProperty("平均行长度")
    private Integer avgRowLength;

    @ApiModelProperty("数据长度")
    private Integer dataLength;

    @ApiModelProperty("最大数据长度")
    private Integer maxDataLength;

    @ApiModelProperty("索引长度")
    private Integer indexLength;

    @ApiModelProperty("空间碎片")
    private Integer dataFree;

    @ApiModelProperty("做自增主键的自动增量当前值")
    private Integer autoIncrement;

    @ApiModelProperty("表的创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("表的更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("表的注释")
    private String tableComment;

}
