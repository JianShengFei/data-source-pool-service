package com.example.mybatisdemo.task;

import cn.hutool.core.collection.CollectionUtil;
import com.example.mybatisdemo.entity.SysDataSource;
import com.example.mybatisdemo.service.IDataSourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author jianshengfei
 * @version 1.0.0
 * @ClassName SysInitTaskService.java
 * @Description 系统初始化任务
 * @createTime 2021年10月31日 13:45
 */
@Component
@Slf4j
public class SysInitTaskService implements ApplicationRunner {

    @Resource
    private IDataSourceService dataScoreService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Set<String> sysDataSources = dataScoreService.getSysDataSources();
        List<SysDataSource> list = dataScoreService.list();
        List<String> dataSources = list.stream().map(SysDataSource::getPoolName).collect(Collectors.toList());
        if(CollectionUtil.isEmpty(sysDataSources) && CollectionUtil.isEmpty(dataSources)) {
            return;
        }
        // 这里无需对刚开始启动时 master 进行存储到db，master作为主要的数据源不允许修改删除
        // 数据库中保存的系统没的  自动注入到系统中
        List<String> collect = dataSources.stream().filter(s -> !sysDataSources.contains(s)).collect(Collectors.toList());

        int i = 0;

        if(CollectionUtil.isNotEmpty(collect)) {
            List<SysDataSource> initDataSources = list.stream().filter(sysDataSource -> collect.contains(sysDataSource.getPoolName()))
                    .collect(Collectors.toList());
            if(CollectionUtil.isNotEmpty(initDataSources)) {
                dataScoreService.saveSysDataScores(initDataSources);
                i += initDataSources.size();
            }
        }
        log.info("初始化数据源完成，成功加载数据源 {} 个", i);

    }
}
