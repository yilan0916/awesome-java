package com.yilan.awesome.genrator;

import java.util.Arrays;

/**
 * @author： yilan0916
 * @date: 2024/6/30
 */
public class Generator {
    public static void main(String[] args) {
        MbpGeneratorConfig config = new MbpGeneratorConfig();
        config.setDatabase("awesome-java");
        config.setProjectName("awesome-java-orm-mp");
        config.setModuleName("mp");
        config.setTableNames(Arrays.asList("mp_test"));
        MbpGeneratorUtil.generate(config);
    }
}
