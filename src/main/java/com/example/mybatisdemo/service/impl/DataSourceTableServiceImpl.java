package com.example.mybatisdemo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.example.mybatisdemo.dto.datasource.table.SysTableInfoQueryDTO;
import com.example.mybatisdemo.entity.SysDataSource;
import com.example.mybatisdemo.mapper.DataSourceMapper;
import com.example.mybatisdemo.service.IDataSourceService;
import com.example.mybatisdemo.service.IDataSourceTableService;
import com.example.mybatisdemo.vo.datasource.table.SysTableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jianshengfei
 * @version 1.0.0
 * @ClassName DataSourceTableServiceImpl.java
 * @Description 系统表【服务类】
 * @createTime 2021年10月31日 19:36
 */
@Service
public class DataSourceTableServiceImpl implements IDataSourceTableService {

    @Resource
    private IDataSourceService dataSourceService;

    @Resource
    private DataSourceMapper dataSourceMapper;

    @Override
    @DS("#dto.dataSource")
    public IPage<SysTableVO> getSysTableInfoPage(SysTableInfoQueryDTO dto) {
        return dataSourceMapper.getSysTableInfoPage(new Page(dto.getPageNum(), dto.getPageSize()), dto);
    }

    @Override
    public void generateCodeByDataSource(String dataSource, List<String> tableNames) {

//        checkDataSourceAndTable(dataSource, tableNames);
    }

    private SysDataSource checkDataSourceAndTable(String dataSource, List<String> tableNames) throws SQLException {
        SysDataSource sysDataSource = dataSourceService.getDataSourceByPoolName(dataSource);

        DataSource sysDataSource1 = dataSourceService.getSysDataSource(dataSource);
        Connection connection = sysDataSource1.getConnection();
//        connection.getMetaData().getTables()
        if(sysDataSource == null) {
            throw new NullPointerException("数据源 不存在");
        }



        return sysDataSource;
    }

    public static void getHostFrom(String url) {
        Pattern p = Pattern.compile("jdbc:(?<db>\\w+):.*((//)|@)(?<host>.+):(?<port>\\d+).*");
        Matcher m = p.matcher(url);
        if(m.find()) {
            System.out.println(m.group("db"));
            System.out.println(m.group("host"));
            System.out.println(m.group("port"));
        }

    }

    public static void main(String[] args) {
        getHostFrom("jdbc:mysql://1.116.164.195:3306/demo?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true\n");
    }


}
