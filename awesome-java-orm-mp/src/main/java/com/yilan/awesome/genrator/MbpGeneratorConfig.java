package com.yilan.awesome.genrator;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author： yilan0916
 * @date: 2024/6/30
 */
@Getter
@Setter
public class MbpGeneratorConfig {

    private Integer step = 0;

    private String jdbcUrl = "jdbc:mysql://localhost:3306";

    private String driverClassName = "com.mysql.cj.jdbc.Driver";

    private String username = "root";

    private String password = "Abcd1234";

    private String database;

    private List<String> tableNames;

    private String tablePrefix = "sys_,t_,c_,de_";

    private String fieldPrefix = "sys_,t_,c_,de_";

    private String parent = "com.yilan.awesome";

    private String moduleName = "";

    private String author = "yilan0916";

    public String getFullJdbcUrl() {
        return jdbcUrl + "/" + database;
    }

    public String getFullPackageUrl() {
        return parent + "." + moduleName;
    }
}
