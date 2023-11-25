package com.yilan.awesome.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.yilan.awesome.base.BaseEntity;
import lombok.extern.slf4j.Slf4j;

import java.sql.Types;
import java.util.Collections;

/**
 * @author： yilan0916
 * @date : 2023/11/25
 */
@Slf4j
public class CodeGenerator {

    public static void main(String[] args) {
        new CodeGenerator().generate();
    }

    private void generate() {
        //项目路径--如果maven是多module则需在后面加module路径
        String projectPath = System.getProperty("user.dir") + "/awesome-java-system";
        String author = "yilan0916";
        String moduleName = "system";
        String packageName = "com.yilan.awesome";
        String[] tables = new String[]{"sys_user"};
        String[] tablePrefix = new String[]{"sys_", "t_"};

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/awesome-java?characterEncoding=utf-8&serverTimezone=GMT%2B8",
                        "root", "Abcd1234")
                // 全局配置
                .globalConfig(builder -> {
                    builder.author(author) // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(projectPath + "/src/main/java"); // 指定输出目录
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
                            .entity("domain")
                            .pathInfo(Collections.singletonMap(OutputFile.xml,
                                    projectPath + "/src/main/resources/mapper/" + moduleName)); // 设置mapperXml生成路径
                })
                // 策略配置
                .strategyConfig(builder -> {
                    builder.addInclude(tables) // 设置需要生成的表名
                            .addTablePrefix(tablePrefix); // 设置过滤表前缀
                    //不继承BaseEntity
//                    builder.entityBuilder().enableLombok().build();
                    //继承BaseEntity
                    builder.entityBuilder().enableLombok()
                            .superClass(BaseEntity.class)
                            .addSuperEntityColumns("create_by", "update_by", "create_time", "update_time", "is_deleted")
                            .build();

                    builder.mapperBuilder().enableMapperAnnotation().build();
                    builder.controllerBuilder().enableHyphenStyle() // 开启驼峰转连字符
                            .enableRestStyle().build(); // 开启生成@RestController 控制器
                    builder.serviceBuilder().formatServiceFileName("%sService").build();

                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
