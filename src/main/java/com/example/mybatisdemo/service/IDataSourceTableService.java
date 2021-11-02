package com.example.mybatisdemo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.mybatisdemo.dto.datasource.table.GenerateCodeDTO;
import com.example.mybatisdemo.dto.datasource.table.SysTableInfoQueryDTO;
import com.example.mybatisdemo.vo.datasource.table.SysTableVO;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

/**
 * @author jianshengfei
 * @version 1.0.0
 * @ClassName IDataSourceTableService.java
 * @Description 系统表【服务类】
 * @createTime 2021年10月31日 19:35
 */
public interface IDataSourceTableService {

    /**
     * 获取系统表(分页)
     * @param dto
     * @return
     */
    IPage<SysTableVO> getSysTableInfoPage(SysTableInfoQueryDTO dto);

    /**
     * 根据选择的表名生成代码
     * @param dto
     * @param dataSource
     */
    void generateCodeByDataSource(GenerateCodeDTO dto, String dataSource);

}
