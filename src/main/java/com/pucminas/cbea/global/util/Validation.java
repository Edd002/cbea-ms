package com.pucminas.cbea.global.util;

import java.util.Collection;

public class Validation {

    public static boolean isNull(Object value) {
        return value == null;
    }

    public static boolean isNotNull(Object value) {
        return !isNull(value);
    }

    public static boolean isEmpty(Collection<?> list) {
        return list == null || list.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> list) {
        return !isEmpty(list);
    }

    public static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isNotBlank(String value) {
        return !isBlank(value);
    }

    public static boolean isJsonObject(String json) {
        return isNotBlank(json) && json.startsWith("{") && json.endsWith("}");
    }

    public static boolean isJsonArray(String json) {
        return isNotBlank(json) && json.startsWith("[") && json.endsWith("]");
    }
}