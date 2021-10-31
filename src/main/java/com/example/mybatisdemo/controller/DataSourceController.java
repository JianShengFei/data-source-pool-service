package com.example.mybatisdemo.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisdemo.dto.datasource.DataScoreQueryDTO;
import com.example.mybatisdemo.entity.SysDataSource;
import com.example.mybatisdemo.service.IDataSourceService;
import com.example.mybatisdemo.vo.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 【系统数据源连接池】 前端控制器
 * </p>
 *
 * @author jianshengfei
 * @since 2021-10-30
 */
@RestController
@RequestMapping("/data-source")
@Slf4j
public class DataSourceController {

    @Autowired
    private IDataSourceService dataScoreService;

    @ApiOperation(value = "获取系统中加载的数据源", notes = "获取系统中加载的数据源")
    @GetMapping("/list")
    public Result list() {
        return Result.success(dataScoreService.getSysDataSources());
    }

    @ApiOperation(value = "获取当前所有数据源(分页)", notes = "获取当前所有数据源(分页)")
    @GetMapping("/page")
    public Result<Page> page(@Valid DataScoreQueryDTO dto) {
        return Result.success(dataScoreService.selectDataScorePage(dto));
    }

    @ApiOperation(value = "根据ID获取数据源", notes = "根据ID获取数据源")
    @GetMapping("/get/byId/{id}")
    public Result list(@PathVariable("id") Long id) {
        return Result.success(dataScoreService.getById(id));
    }

    @ApiOperation(value = "保存数据源", notes = "保存数据源")
    @PostMapping("/save")
    public Result<Boolean> add(@Valid @RequestBody SysDataSource dto) {
        return Result.success(dataScoreService.saveDataScore(dto));
    }

    @ApiOperation(value = "删除数据源", notes = "删除数据源")
    @DeleteMapping("/remove/{dataScoreName}")
    public Result<Boolean> remove(@PathVariable("dataScoreName") String dataScoreName) {
        return Result.success(dataScoreService.deleteDataScore(dataScoreName));
    }



}
