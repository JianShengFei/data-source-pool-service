package com.example.mybatisdemo.datasource.table;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
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

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
//        dto.setTableSchema("jsf_test_1");
//        dto.setTableSchema("demo");
        IPage<SysTableVO> sysTableInfoPage = dataSourceTableService.getSysTableInfoPage(dto);
        System.out.println(sysTableInfoPage);
    }


    @Test
    public void test03() throws SQLException {
        initDataSources();
        DataSource dataSource = dataSourceService.getSysDataSource("jsf_test_1");
        Connection connection = dataSource.getConnection();

        List<String> allTables = getAllTables(connection, null);
        List<String> primaryKeys = getPrimaryKeys(connection, "tb_user_info");
        List<Map<String, String>> allColumns = getAllColumns(connection, "tb_user_info");

        System.out.println("all tables:");
        allTables.stream().forEach(table -> System.out.println(table + "\n"));
        System.out.println("table's primaryKeys:");
        primaryKeys.stream().forEach(primaryKey -> System.out.println(primaryKey + "\n"));
        System.out.println("table's all columns:");
        allColumns.stream().forEach(map -> {
            map.forEach((k, v) -> {
                System.out.println(k + ":" + v + "\t");
            });
            System.out.println("\n");
        });



    }

    /**
     * get all tables from DataSource - Connection - DatabaseMetaData
     * use %tableNamePattern% to get fuzzy matching all tables
     * eg: ResultSet tables2 = metaData.getTables(null,null,"%tableNamePattern%",new String[]{"TABLE"});
     *
     * @param connection
     * @param tableNamePattern
     * @return
     * @throws SQLException
     */
    public static List<String> getAllTables(Connection connection, String tableNamePattern) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tables = metaData.getTables(null, null, tableNamePattern, new String[]{"TABLE"});
        List<String> tableList = new ArrayList<>();
        while (tables.next()) {
            String tableName = tables.getString(3);
            tableList.add(tableName);
        }
        return tableList;
    }

    /**
     * get all PrimaryKeys from DataSource - Connection - DatabaseMetaData
     *
     * @param connection
     * @param tableNamePattern
     * @return
     * @throws SQLException
     */
    public static List<String> getPrimaryKeys(Connection connection, String tableNamePattern) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet primaryKeys = metaData.getPrimaryKeys(null, null, tableNamePattern);
        List<String> primaryKeyList = new ArrayList<>();
        while (primaryKeys.next()) {
            String columnName = primaryKeys.getString("COLUMN_NAME");
            primaryKeyList.add(columnName);
        }
        return primaryKeyList;
    }

    /**
     * get all columns from table by DataSource - Connection - DatabaseMetaData
     *
     * @param connection
     * @param tableNamePattern
     * @return
     * @throws SQLException
     */
    public static List<Map<String, String>> getAllColumns(Connection connection, String tableNamePattern) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet columns = metaData.getColumns(null, null, tableNamePattern, null);
        List<Map<String, String>> columnList = new ArrayList<>();
        while (columns.next()) {
            // 可自定义column对象用于存储列信息
            Map<String, String> columnMap = new HashMap<>();
            String tableCat = columns.getString("TABLE_CAT"); // 库名 schema
            columnMap.put("tableCat", tableCat);
            String tableName = columns.getString("TABLE_NAME"); // 表名
            columnMap.put("tableName", tableName);
            String columnName = columns.getString("COLUMN_NAME"); // 列名
            columnMap.put("columnName", columnName);
            String dataType = columns.getString("DATA_TYPE"); // 数据类型
            columnMap.put("dataType", dataType);
            String typeName = columns.getString("TYPE_NAME"); // 列的数据类型：INT、VARCHAR
            columnMap.put("typeName", typeName);
            String columnSize = columns.getString("COLUMN_SIZE"); // 列的数据类型的大小： 1 10 32
            columnMap.put("columnSize", columnSize);
            String nullAble = columns.getString("NULLABLE"); // 允许为空：0 不允许 1 允许
            columnMap.put("nullAble", nullAble);
            String remarks = columns.getString("REMARKS"); // 备注
            columnMap.put("remarks", remarks);
            String columnDef = columns.getString("COLUMN_DEF"); // 列的默认值
            columnMap.put("columnDef", columnDef);
            String ordinalPosition = columns.getString("ORDINAL_POSITION"); // 表字段创建的顺序
            columnMap.put("ordinalPosition", ordinalPosition);
            String isNullAble = columns.getString("IS_NULLABLE"); // 表字段是否允许为空："YES":允许为null "NO"：不允许为null
            columnMap.put("isNullAble", isNullAble);
            String isAutoIncrement = columns.getString("IS_AUTOINCREMENT"); // 是否是自增列 "YES"：是 "NO"：不是
            columnMap.put("isAutoIncrement", isAutoIncrement);
            columnList.add(columnMap);
        }
        return columnList;
    }


}
