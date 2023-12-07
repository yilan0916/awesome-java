package com.yilan.awesome.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * @author： yilan0916
 * @date : 2023/7/4
 */
public class EntityExistException extends RuntimeException{

    public EntityExistException(Class clazz, String field, String val) {
        super(EntityExistException.generateMessage(clazz.getSimpleName(), field, val));
    }

    private static String generateMessage(String entity, String field, String val) {
        return StringUtils.capitalize(entity)
                + "with" + field + " " + val + " existed";
    }
}
