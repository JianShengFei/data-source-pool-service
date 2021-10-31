package com.example.mybatisdemo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisdemo.dto.datasource.table.SysTableInfoQueryDTO;
import com.example.mybatisdemo.mapper.DataSourceMapper;
import com.example.mybatisdemo.service.IDataSourceTableService;
import com.example.mybatisdemo.vo.datasource.table.SysTableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jianshengfei
 * @version 1.0.0
 * @ClassName DataSourceTableServiceImpl.java
 * @Description 系统表【服务类】
 * @createTime 2021年10月31日 19:36
 */
@Service
public class DataSourceTableServiceImpl implements IDataSourceTableService {

    @Autowired
    private DataSourceMapper dataSourceMapper;

    @Override
    @DS("#dto.dataSource")
    public IPage<SysTableVO> getSysTableInfoPage(SysTableInfoQueryDTO dto) {
        return dataSourceMapper.getSysTableInfoPage(new Page(dto.getPageNum(), dto.getPageSize()), dto);
    }





}
