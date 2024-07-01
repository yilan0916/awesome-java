package com.yilan.awesome.genrator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.Setter;

import java.io.File;
import java.sql.Types;
import java.util.*;

/**
 * @author： yilan0916
 * @date: 2024/6/30
 */
@Setter
public class CodeGenerator {

    // 数据库
    private String url = "jdbc:mysql://localhost:3306/awesome-java?characterEncoding=utf-8&serverTimezone=GMT%2B8";
    private String username = "root";
    private String password = "Abcd1234";

    private String projectPath = System.getProperty("user.dir") + "/awesome-java-orm-mp";
    private String author = "yilan0916";
    private String moduleName = "mp";
    private String packageName = "com.yilan.awesome";
    private String[] tables = new String[]{"sys_job"};
    private String[] tablePrefix = new String[]{"sys_"};
    private String[] fieldPrefix = new String[]{""};

    public static void main(String[] args) {
        new CodeGenerator().generator();
    }

    private void generator() {


        FastAutoGenerator.create(url, username, password)
                // 全局配置
                .globalConfig(builder -> {
                    builder.outputDir(projectPath + "/src/main/java") // 指定输出目录
                            .author(author) // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .disableOpenDir(); // 关闭自动打开输出目录
                })
                // 数据源配置
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                // 包配置
                .packageConfig(builder -> {
                    builder.parent(packageName) // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .controller("rest")
                            .entity("domain.entity")
                            .pathInfo(Collections.singletonMap(OutputFile.xml,
                                    projectPath + "/src/main/resources/mapper/" + moduleName))
                            .joinPackage("domain.criteria"); // 设置mapperXml生成路径
                })
                // 策略配置
                .strategyConfig(builder -> {
                    builder.addInclude(tables) // 设置需要生成的表名
                            .addTablePrefix(tablePrefix) // 设置过滤表前缀
                            .addFieldPrefix(fieldPrefix); //设置过滤字段前缀
                    builder.entityBuilder().enableLombok().build();
                    builder.mapperBuilder().enableMapperAnnotation().build();
                    builder.controllerBuilder().enableHyphenStyle() // 开启驼峰转连字符
                            .enableRestStyle().build(); // 开启生成@RestController 控制器
                    builder.serviceBuilder().formatServiceFileName("%sService").build();

                })
                // 注入配置
                .injectionConfig(builder -> {
                    // TODO 未完成
                    // 自定义生成模板参数
                    Map<String, Object> paramMap = new HashMap<>();

                    paramMap.put("Criteria", projectPath + "/src/main/java/" + moduleName + "/domain/criteria");

                    // 自定义生成类，如DTO QueryCriteria
                    List<CustomFile> customFiles = new ArrayList<>();
                    customFiles.add(new CustomFile.Builder().packageName("domain/criteria").fileName("QueryCriteria.java")
                            .templatePath("/templates/queryCriteria.java.ftl").enableFileOverride().build());
                    builder.customMap(paramMap).customFile(customFiles);
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
