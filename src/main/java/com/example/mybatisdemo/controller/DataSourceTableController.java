package com.example.mybatisdemo.controller;

import com.example.mybatisdemo.dto.datasource.table.SysTableInfoQueryDTO;
import com.example.mybatisdemo.service.IDataSourceTableService;
import com.example.mybatisdemo.vo.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 数据源表操作 【前端控制器】
 * @author jianshengfei
 * @version 1.0.0
 * @ClassName DataSourceTableController.java
 * @Description 数据源表操作 【前端控制器】
 * @createTime 2021年10月31日 14:45
 */
@RestController
@RequestMapping("/data-source-table")
public class DataSourceTableController {

    @Resource
    private IDataSourceTableService dataSourceTableService;

    @ApiOperation("获取数据源下的所有的表")
    @GetMapping("/page")
    public Result getDataSourceTable(@Valid SysTableInfoQueryDTO dto) {
        return Result.success(dataSourceTableService.getSysTableInfoPage(dto));
    }


}
