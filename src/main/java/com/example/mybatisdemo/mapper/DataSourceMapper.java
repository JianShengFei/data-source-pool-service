package com.example.mybatisdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.mybatisdemo.dto.datasource.table.SysTableInfoQueryDTO;
import com.example.mybatisdemo.entity.SysDataSource;
import com.example.mybatisdemo.vo.datasource.table.SysTableVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 【系统数据源连接池】 Mapper 接口
 * </p>
 *
 * @author jianshengfei
 * @since 2021-10-30
 */
@Mapper
public interface DataSourceMapper extends BaseMapper<SysDataSource> {

    /**
     * 分页查询系统表
     * @param page
     * @param dto
     * @return
     */
    IPage<SysTableVO> getSysTableInfoPage(IPage page, @Param("dto") SysTableInfoQueryDTO dto);

}
