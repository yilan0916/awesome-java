package com.yilan.awesome.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

/**
 * @author： yilan0916
 * @date: 2024/6/30
 */
@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "database-backup")
public class DatabaseBackupConfig {

    private String ip;
    private String port;
    private String username;
    private String password;
    private String databaseName;
    private String savePath;
    private String filename;

    @Value("${spring.profiles.active}")
    private String env;

//    @Scheduled(cron = "0 0 23 * * ?")
    public void task() {
        LocalDate now = LocalDate.now();
        // 备份数据库
        backUpDatabase(now);
        // 删除30天前的备份数据
        deleteFile(now);
    }

    private void backUpDatabase(LocalDate date) {
        File file = new File(savePath);
        if (!file.exists()) {
            // 目录不存在创建新的文件夹
            file.mkdirs();
        }

        File dataFile = new File(savePath + date + filename);

        String cmd = "mysqldump --opt --single-transaction" +
                " -h" + ip + " -P" + port + " -u" + username + " -p" + password
                + " " + databaseName + " > " + dataFile;
        log.info(cmd);

        //win: cmd /c
        try {
            Process exec;
            if (StringUtils.equals(env, "dev")) {
                exec = Runtime.getRuntime().exec(new String[]{"cmd", "/c", cmd});
            } else {
                exec = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", cmd});
            }

            if (exec.waitFor() == 0) {
                log.info("数据库备份成功");
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteFile(LocalDate now) {
        LocalDate before = now.minusMonths(1);
        File file = new File(savePath + before + filename);
        if (file.exists()) {
            if (file.delete()) {
                log.info("数据库备份-删除文件成功" + file.getName());
            } else {
                log.info("数据库备份-删除文件删除" + file.getName());

            }
        }
    }

    public static void main(String[] args) {
        // mysqldump -uroot -pAbcd1234 awesome-java > /Users/yilan/backup/abc123.sql
        new DatabaseBackupConfig().task();
    }
}
