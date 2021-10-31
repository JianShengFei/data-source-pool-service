package com.example.mybatisdemo.datasource.table;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisdemo.dto.datasource.table.SysTableInfoQueryDTO;
import com.example.mybatisdemo.entity.SysDataSource;
import com.example.mybatisdemo.mapper.DataSourceMapper;
import com.example.mybatisdemo.service.IDataSourceService;
import com.example.mybatisdemo.service.IDataSourceTableService;
import com.example.mybatisdemo.vo.datasource.table.SysTableVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * @author jianshengfei
 * @version 1.0.0
 * @ClassName SysTableTest.java
 * @Description 系统表操作测试
 * @createTime 2021年10月31日 18:27
 */
@SpringBootTest
public class SysTableTest {

    @Autowired
    private DataSourceMapper dataSourceMapper;

    @Test
    public void test01() {
        SysTableInfoQueryDTO dto = new SysTableInfoQueryDTO();
        dto.setTableSchema("demo");
        IPage<SysTableVO> tableInfoPage = dataSourceMapper.getSysTableInfoPage(new Page(1, 10), dto);
        System.out.println(JSONUtil.toJsonStr(tableInfoPage));
    }

    @Autowired
    private IDataSourceTableService dataSourceTableService;

    @Autowired
    private IDataSourceService dataSourceService;

    /**
     * 初始化数据源
     */
    private void initDataSources() {
        SysDataSource dataSource1 = new SysDataSource();
        dataSource1.setPoolName("jsf_test_1");
        dataSource1.setUrl("jdbc:mysql://159.75.82.245:3306/jsf_test_1?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true");
        dataSource1.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource1.setUsername("root");
        dataSource1.setPassword("houyi..");
        dataSourceService.saveSysDataScore(dataSource1);

        SysDataSource dataSource2 = new SysDataSource();
        dataSource2.setPoolName("jsf_test_2");
        dataSource2.setUrl("jdbc:mysql://159.75.82.245:3306/jsf_test_1?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true");
        dataSource2.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource2.setUsername("root");
        dataSource2.setPassword("houyi..");
        dataSourceService.saveSysDataScore(dataSource2);

    }

    @Test
    public void test02() {
        // 这里如果不初始化下 数据源的话，导致找不到对应的数据源 默认回去master查询
        initDataSources();
        SysTableInfoQueryDTO dto = new SysTableInfoQueryDTO();
        dto.setPageNum(1);
        dto.setPageSize(10);
        dto.setDataSource("jsf_test_1");
        dto.setTableSchema("jsf_test_1");
//        dto.setTableSchema("demo");
        IPage<SysTableVO> sysTableInfoPage = dataSourceTableService.getSysTableInfoPage(dto);
        System.out.println(sysTableInfoPage);
    }

}
