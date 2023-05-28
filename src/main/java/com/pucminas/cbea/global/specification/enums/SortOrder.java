package com.pucminas.cbea.global.specification.enums;

public enum SortOrder {
    ASC,
    DESC,
    EMPTY,
    BOTH;

    public static SortOrder valueOfIgnoreCase(String sortOrder) {
        sortOrder = sortOrder.toUpperCase();
        return valueOf(sortOrder);
    }
}