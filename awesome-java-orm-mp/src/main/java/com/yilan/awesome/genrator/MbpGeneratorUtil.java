package com.yilan.awesome.genrator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.yilan.awesome.base.BaseEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.*;

/**
 * @author： yilan0916
 * @date: 2024/6/30
 */
public class MbpGeneratorUtil {

    private static final String OUTPUT_PATH = System.getProperty("user.dir");

    private MbpGeneratorUtil() {
    }

    public static void generate(MbpGeneratorConfig config) {
        // 1、配置数据源
        FastAutoGenerator.create(config.getFullJdbcUrl(), config.getUsername(), config.getPassword())
                // 2、全局配置
                .globalConfig(builder -> {
                    builder.disableOpenDir() // 禁止打开输出目录 默认 true
                            .outputDir(OUTPUT_PATH + "/awesome-java-orm-mp/src/main/java")   // 设置输出路径：项目的 java 目录下
                            .author(config.getAuthor()) // 设置作者名
                            // .enableKotlin() // 开启 Kotlin 模式 默认false
                            .enableSwagger() // 开启 Swagger 模式 默认false
                            .dateType(DateType.TIME_PACK) // 设置时间类型策略 TIME_PACK=LocalDateTime;ONLY_DATE=Date;
                            // .commentDate("yyyy-MM-dd") // 设置注释日期格式 默认值 yyyy-MM-dd
                            .build();
                })
                // 3、包配置
                .packageConfig(builder -> {
                    builder.parent(config.getParent()) // 父包名 默认值 com.baomidou
                            .moduleName(config.getModuleName())   // 父包模块名 默认值 无
                            .entity("domain.entity")   // Entity 包名 默认值 entity
                            // .service("service") //Service 包名 默认值 service
                            // .serviceImpl("service.impl") // Service Impl 包名 默认值:service.impl
                            //.mapper("mapper")   // Mapper 包名 默认值 mapper
                            // .xml("mapper")  // Mapper XML 包名 默认值 mapper.xml
                            .controller("rest") // Controller 包名 默认值 controller
                            .pathInfo(Collections.singletonMap(OutputFile.xml, OUTPUT_PATH + "/src/main/resources/mapper/" + config.getModuleName()))  //配置 mapper.xml 路径信息：项目的 resources 目录下
                            .build();
                })
                // 4、模版配置
                .templateConfig(builder -> {
                    builder.entity("/templates/entity.java")
                            .service("/templates/service.java")
                            .serviceImpl("/templates/serviceImpl.java")
                            .mapper("/templates/mapper.java")
                            .xml("/templates/mapper.xml")
                            .controller("/templates/controller.java")
                            // .disable(TemplateType.ENTITY) // 禁用实体类生成
                            .build();
                })
                // 5、策略配置
                .strategyConfig(builder -> {
                    builder.addInclude(config.getTableNames()) // 设置需要生成的数据表名
                            .addTablePrefix(config.getTablePrefix().split(",")) // 设置过滤表前缀
                            .addFieldPrefix(config.getFieldPrefix()) //设置过滤字段前缀

                            // 5.1、实体类策略配置
                            .entityBuilder()
                            .enableFileOverride() // 覆盖entity
                            .superClass(BaseEntity.class)
                            // .disableSerialVersionUID()  // 禁用生成 serialVersionUID 默认值 true
                            // .enableChainModel() // 开启链式调用
                            // .enableRemoveIsPrefix() // 开启 Boolean 类型字段移除 is 前缀
                            .enableLombok() // 开启 Lombok 默认值:false
                            .enableTableFieldAnnotation()       // 开启生成实体时生成字段注解 默认值 false
                            // .enableActiveRecord() // 开启 AR 默认值为false
                            .logicDeleteColumnName("is_deleted")   // 逻辑删除字段名
                            .naming(NamingStrategy.underline_to_camel)  //数据库表映射到实体的命名策略：下划线转驼峰命
                            .columnNaming(NamingStrategy.underline_to_camel)    // 数据库表字段映射到实体的命名策略：下划线转驼峰命
                            .addSuperEntityColumns("create_by", "create_time", "update_by", "update_time")
                            // .addIgnoreColumns("age")
//                            .addTableFills(
//                                    new Column("creator", FieldFill.INSERT),
//                                    new Column("updater", FieldFill.INSERT_UPDATE)
//                            )   // 添加表字段填充，"create_time"字段自动填充为插入时间，"modify_time"字段自动填充为插入修改时间
                            .idType(IdType.ASSIGN_ID) // 雪花算法
                            .formatFileName("%s") // 默认是 %sEntity

                            // 5.2、Mapper策略配置
                            .mapperBuilder()
                            .enableFileOverride() // 覆盖mapper
//                            .superClass(BaseMapperX.class)   // 设置父类 默认是BaseMapper.class
                            .mapperAnnotation(Mapper.class)      // 开启 @Mapper 注解
                            // .enableBaseResultMap() //启用 BaseResultMap 生成
                            .formatMapperFileName("%sMapper")   // 格式化 mapper 文件名称
                            .formatXmlFileName("%sMapper") // 格式化 Xml 文件名称

                            //5.3、service 策略配置
                            .serviceBuilder()
                            .enableFileOverride() // 覆盖service
                            .formatServiceFileName("%sService") // 格式化 service 接口文件名称，%s进行匹配表名，如 UserService
                            .formatServiceImplFileName("%sServiceImpl") // 格式化 service 实现类文件名称，%s进行匹配表名，如 UserServiceImpl

                            //5.4、Controller策略配置
                            .controllerBuilder()
                            .enableFileOverride() // 覆盖controller
                            .enableRestStyle()  // 开启生成 @RestController 控制器
                            //.enableHyphenStyle() //开启驼峰转连字符 默认false
                            .formatFileName("%sController") // 格式化 Controller 类文件名称，%s进行匹配表名，如 UserController
                            .build();
                })
                // 6、自定义配置
                .injectionConfig(consumer -> {
                    Map<String, Object> customMap = new HashMap<>();
                    customMap.put("dto", config.getFullPackageUrl() + ".domain.dto");
                    customMap.put("vo", config.getFullPackageUrl() + ".domain.vo");
                    customMap.put("criteria", config.getFullPackageUrl() + ".domain.criteria");
                    customMap.put("convert", config.getFullPackageUrl() + ".convert");

                    consumer.customMap(customMap);
                    // DTO
                    List<CustomFile> customFiles = new ArrayList<>();
                    customFiles.add(new CustomFile.Builder().packageName("domain.dto").fileName("DTO.java")
                            .templatePath("/templates/others/dto.java.ftl").enableFileOverride().build());
                    customFiles.add(new CustomFile.Builder().packageName("domain.vo").fileName("VO.java")
                            .templatePath("/templates/others/vo.java.ftl").enableFileOverride().build());
                    customFiles.add(new CustomFile.Builder().packageName("domain.criteria").fileName("QueryCriteria.java")
                            .templatePath("/templates/others/queryCriteria.java.ftl").enableFileOverride().build());
                    customFiles.add(new CustomFile.Builder().packageName("convert").fileName("Convert.java")
                            .templatePath("/templates/others/convert.java.ftl").enableFileOverride().build());
                    consumer.customFile(customFiles);
                })
                // 7、模板
                .templateEngine(new FreemarkerTemplateEngine())
                // 8、执行
                .execute();

    }
}
