package com.pucminas.cbea.global.specification.specification;

import lombok.Data;

@Data
public class SearchCriteria {

    private String key;
    private SearchOperation operation;
    private Object value;
    private Object param;

    public SearchCriteria() { }

    public SearchCriteria(final String key, final SearchOperation operation) {
        this.key = key;
        this.operation = operation;
    }

    public SearchCriteria(final String key, final SearchOperation operation, final Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public SearchCriteria(String key, SearchOperation operation, Object value, Object param) {
        this.key = key;
        this.operation = operation;
        this.value = value;
        this.param = param;
    }
}