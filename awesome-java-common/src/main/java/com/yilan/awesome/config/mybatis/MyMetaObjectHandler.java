package com.yilan.awesome.config.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author： yilan0916
 * @date: 2024/6/30
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        /* 创建时间 */
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        /* 操作人 */
//        String username = "System";
//        try {username = SecurityUtils.getCurrentUsername();}catch (Exception ignored){}
//        this.strictInsertFill(metaObject, "createBy", String.class, username);
//        this.strictInsertFill(metaObject, "updateBy", String.class, username);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        /* 更新时间 */
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        /* 操作人 */
//        String username = "System";
//        try {username = SecurityUtils.getCurrentUsername();}catch (Exception ignored){}
//        this.strictUpdateFill(metaObject, "updateBy", String.class, username);
    }
}
