package com.example.mybatisdemo.config;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

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


    public static void main(String[] args) {

        FastAutoGenerator.create("jdbc:mysql://1.116.164.195:3306/demo?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true",
                        "root",
                        "1213123123")
                .globalConfig((scanner, builder) -> {
                    builder.author(getAuthor(scanner.apply("请输入作者名称？(输入default 默认jianshengfei)"))) // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(packPath); // 指定输出目录
                })
                .packageConfig((scanner, builder) -> {
                    builder.parent(scanner.apply("请输入包名？")) // 设置父包名
                            .moduleName(scanner.apply("请输入父包模块名？")) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, packPath)); // 设置mapperXml生成路径
                })
                .strategyConfig((scanner, builder) -> {
                    builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all"))) // 设置需要生成的表名
                            .addTablePrefix(getTablePrefix(scanner.apply("请输入过滤的表前缀？(多个逗号分隔, 默认添加下划线)"))) // 设置过滤表前缀
                            .controllerBuilder() // 控制层策略配置
//                            .superClass(BaseController)
                            .enableRestStyle()  // 开启生成@RestController 控制器
                            .enableHyphenStyle() // 开启驼峰转连字符
                            .entityBuilder() // 实体类策略配置
                            .enableLombok() //开启Lombok注解
                    ;
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    // 处理表前缀
    private static String getTablePrefix(String tablePrefixes) {
//        if(StringUtils.isBlank(tablePrefixes)) {
//            return "sys_";
//        }
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
}