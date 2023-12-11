package com.yilan.awesome.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Date;

/**
 * @author： yilan0916
 * @date : 2023/12/7
 */
@Slf4j
@Component
public class DbUtils {


    private static final String ip = "192.168.31.243";
    private static final String port = "3306";
    private static final String username = "root";
    private static final String password = "Abcd1234";
    private static final String databaseName = "awesome-java";
    private static final String savePath = "/Users/yilan/backup";
    private static final String fileName = "";


    public static void backUpDatabase() {
        File file = new File(savePath);
        if (!file.exists()) {
            // 目录不存在创建新的文件夹
            file.mkdirs();
        }

        Date date = new Date();

        File dataFile = new File(savePath + "/129.sql");


        String cmd = "mysqldump --opt --single-transaction" +
                " -h" + ip + " -P" + port + " -u" + username + " -p" + password
                + " " + databaseName + " > " + dataFile;
        System.out.println(cmd);

        //win: cmd /c
        try {
            Process exec = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", cmd});
            if (exec.waitFor() == 0) {
                log.info("数据库备份成功");
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        // mysqldump -uroot -pAbcd1234 awesome-java > /Users/yilan/backup/abc123.sql
        DbUtils.backUpDatabase();
    }
}
