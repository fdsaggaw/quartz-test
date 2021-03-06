package com.su.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        System.out.println("新增方法实体填充");
        LocalDateTime now = LocalDateTime.now();
        this.setFieldValByName("creatorId", 1, metaObject);
        this.setFieldValByName("creatorName", "admin", metaObject);
        this.setFieldValByName("createdTime", now, metaObject);
        this.setFieldValByName("deleteFlag", false, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        System.out.println("更新方法实体填充");
        this.setFieldValByName("updatedTime", LocalDateTime.now(), metaObject);
    }

}
