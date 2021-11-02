package com.example.mybatisdemo.config;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.example.mybatisdemo.dto.datasource.table.GenerateCodeDTO;
import com.example.mybatisdemo.entity.SysDataSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jianshengfei
 * @version 1.0.0
 * @ClassName GeneratorCodeConfig.java
 * @Description 代码自动生成器  mybatis plus 3.5+ 代码生成器 https://baomidou.com/guide/generator-new.html#%E5%BF%AB%E9%80%9F%E5%85%A5%E9%97%A8
 * @createTime 2021年10月29日 17:51
 */
public class GeneratorCodeConfig {

    private static final String authorName = "jianshengfei";

    private static final String packPath = "D://code";


//    public static void main(String[] args) {
//        generateCode();
//    }

    public static void generateCode(GenerateCodeDTO dto, SysDataSource dataSource) {
        FastAutoGenerator.create(dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword())
                .globalConfig((scanner, builder) -> {
                    builder.author(getAuthor(dto.getAuthor())) // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(getOutputPath(dto.getOutputPath())); // 指定输出目录
                })
                .packageConfig((scanner, builder) -> {
                    builder.parent(dto.getParentPackageName()) // 设置父包名
                            .moduleName(dto.getModuleName()) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, getOutputPath(dto.getOutputPath()))) // 设置mapperXml生成路径
                    ;
                })
                .strategyConfig((scanner, builder) -> {
                    builder.addInclude(getTables(dto.getTables())) // 设置需要生成的表名
                            .addTablePrefix(getTablePrefix(dto.getTablePrefix())) // 设置过滤表前缀
                            .controllerBuilder() // 控制层策略配置
//                            .superClass(BaseController)
                            .enableRestStyle()  // 开启生成@RestController 控制器
                            .enableHyphenStyle() // 开启驼峰转连字符
                            .entityBuilder() // 实体类策略配置
                            .enableLombok() //开启Lombok注解
                    ;
                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }

    // 处理表前缀
    private static String getTablePrefix(String tablePrefixes) {
        if(StringUtils.isBlank(tablePrefixes)) {
            return "sys_";
        }
        List<String> collect = Arrays.stream(tablePrefixes.split(",")).collect(Collectors.toList());
        collect.forEach(e -> e += "_");
        return collect.stream().collect(Collectors.joining(","));
    }

    // get作者
    private static String getAuthor(String apply) {
        return StringUtils.isNotBlank(apply) && "default".equals(apply) ? apply : authorName;
    }

    // 处理 all 情况
    private static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

    private static String getOutputPath(String path) {
        return StringUtils.isNotBlank(path) ? path : packPath;
    }
}