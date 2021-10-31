package com.example.mybatisdemo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.DefaultDataSourceCreator;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatisdemo.dto.datasource.DataScoreQueryDTO;
import com.example.mybatisdemo.entity.SysDataSource;
import com.example.mybatisdemo.mapper.DataSourceMapper;
import com.example.mybatisdemo.service.IDataSourceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 【系统数据源连接池】 服务实现类
 * </p>
 *
 * @author jianshengfei
 * @since 2021-10-30
 */
@Service
public class DataSourceServiceImpl extends ServiceImpl<DataSourceMapper, SysDataSource> implements IDataSourceService {

    @Resource
    private DataSource dataSource;

    @Resource
    private DefaultDataSourceCreator dataSourceCreator;

    @Override
    public Set<String> getSysDataSources() {
        try {
            DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
            return ds.getDataSources().keySet();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public DataSource getSysDataSource(String dataSourceName) {
        if(StringUtils.isBlank(dataSourceName)) {
            throw new NullPointerException("数据源名称 不能为空");
        }
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        return ds.getDataSource(dataSourceName);
    }

    @Override
    public Boolean saveSysDataScore(SysDataSource dto) {
        try {
            DataSourceProperty dataSourceProperty = new DataSourceProperty();
            BeanUtils.copyProperties(dto, dataSourceProperty);
            DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
            DataSource dataSource = dataSourceCreator.createDataSource(dataSourceProperty);
            ds.addDataSource(dto.getPoolName(), dataSource);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void saveSysDataScores(List<SysDataSource> dos) {
        if(CollectionUtil.isEmpty(dos)) {
            return;
        }
        for (SysDataSource d : dos) {
            this.saveDataScore(d);
        }
    }

    @Override
    public Boolean deleteSysDataScore(String dataSourceName) {
        try {
            DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
            ds.removeDataSource(dataSourceName);
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public IPage<SysDataSource> selectDataScorePage(DataScoreQueryDTO dto) {
        LambdaQueryWrapper<SysDataSource> q = new LambdaQueryWrapper<>();
        return this.page(new Page<>(dto.getPageNum(), dto.getPageSize()), q);
    }

    @Override
    public SysDataSource checkDataScoreExistAndReturn(String dataSourceName) {

        if(StringUtils.isBlank(dataSourceName)) {
            throw new NullPointerException("数据源名称 不能为空");
        }

        DataSource dataSource = getSysDataSource(dataSourceName);

        if(dataSource == null) {
            return null;
        }

        LambdaQueryWrapper<SysDataSource> q = new LambdaQueryWrapper<>();
        q.eq(SysDataSource::getPoolName, dataSourceName);
        return this.getOne(q);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean saveDataScore(SysDataSource dto) {
        SysDataSource sysDataSource = checkDataScoreExistAndReturn(dto.getPoolName());

        if(dto.getId() == null) {
            if(sysDataSource != null) {
                throw new RuntimeException("数据源 已存在");
            }
        }else {
            if(sysDataSource == null) {
                throw new RuntimeException("数据源 不存在");
            }
            sysDataSource.setLastUpdateTime(LocalDateTime.now());
        }
        if(sysDataSource == null) {
            sysDataSource = new SysDataSource();
            sysDataSource.setCreateTime(LocalDateTime.now());
        }

        BeanUtil.copyProperties(dto, sysDataSource, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
        if(!this.saveOrUpdate(sysDataSource) || !saveSysDataScore(dto)) {
            throw new RuntimeException("保存失败");
        }
        return true;
    }

    @Override
    public Boolean deleteDataScore(String dataSourceName) {
        SysDataSource sysDataSource = checkDataScoreExistAndReturn(dataSourceName);
        if(sysDataSource == null) {
            throw new NullPointerException("数据源 不存在");
        }
        if(!this.removeById(sysDataSource.getId()) || !deleteSysDataScore(dataSourceName)) {
            throw new RuntimeException("删除失败");
        }
        return true;
    }


}
