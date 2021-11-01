package com.example.mybatisdemo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisdemo.dto.datasource.DataScoreQueryDTO;
import com.example.mybatisdemo.entity.SysDataSource;

import javax.sql.DataSource;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 【系统数据源连接池】 服务类
 * </p>
 *
 * @author jianshengfei
 * @since 2021-10-30
 */
public interface IDataSourceService extends IService<SysDataSource> {

    /**
     * 获取系统的数据源
     * @return
     */
    Set<String> getSysDataSources();

    /**
     * 根据数据源名称获取数据源
     * @param dataSourceName
     * @return
     */
    DataSource getSysDataSource(String dataSourceName);

    /**
     * 保存数据源
     * @param dto
     * @return
     */
    Boolean saveSysDataScore(SysDataSource dto);

    /**
     * 根据数据源名称获取数据源
     * @param poolName
     * @return
     */
    SysDataSource getDataSourceByPoolName(String poolName);

    /**
     * 批量保存数据源
     * @param dos
     */
    void saveSysDataScores(List<SysDataSource> dos);

    /**
     * 删除数据源
     * @param dataSourceName
     * @return
     */
    Boolean deleteSysDataScore(String dataSourceName);

    /**
     * 查询数据源列表(分页)
     * @param dto
     * @return
     */
    IPage<SysDataSource> selectDataScorePage(DataScoreQueryDTO dto);


    /**
     * 检查数据库是否存在 存在则返回
     * 判定同一数据库的维度是到数据库名  ip:port/dataBaseName
     * @param dataSourceName
     * @return
     */
    SysDataSource checkDataScoreExistAndReturn(String dataSourceName);

    /**
     * 保存数据源
     * @param dto
     * @return
     */
    Boolean saveDataScore(SysDataSource dto);

    /**
     * 删除数据源
     * @param dataSourceName
     * @return
     */
    Boolean deleteDataScore(String dataSourceName);

}
