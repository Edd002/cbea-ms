package com.pucminas.cbea.global.specification;

import com.pucminas.cbea.global.specification.filter.BaseFilter;
import com.pucminas.cbea.global.specification.specification.SearchCriteria;
import com.pucminas.cbea.global.specification.specification.SearchOperation;
import com.pucminas.cbea.global.util.Validation;
import lombok.extern.java.Log;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log
public abstract class BasicSpecificationBuilder<TYPE, SPECIFICATION extends Specification<TYPE>, FILTER extends BaseFilter> {

    protected List<SearchCriteria> withs;
    protected List<SearchCriteria> secondaryWiths;
    protected List<SearchCriteria> wheres;

    public BasicSpecificationBuilder() {
        withs = new ArrayList<>();
        wheres = new ArrayList<>();
        secondaryWiths = new ArrayList<>();
    }

    protected BasicSpecificationBuilder<TYPE, SPECIFICATION, FILTER> secundaryWith(String key, SearchOperation operation, Object value) {
        secondaryWiths.add(new SearchCriteria(key, operation, value));
        return this;
    }

    protected BasicSpecificationBuilder<TYPE, SPECIFICATION, FILTER> with(String key, SearchOperation operation, Object value) {
        withs.add(new SearchCriteria(key, operation, value));
        return this;
    }

    protected BasicSpecificationBuilder<TYPE, SPECIFICATION, FILTER> with(String key, SearchOperation operation, Object value, Object param) {
        withs.add(new SearchCriteria(key, operation, value, param));
        return this;
    }

    protected BasicSpecificationBuilder<TYPE, SPECIFICATION, FILTER> where(String key, SearchOperation operation, Object value) {
        wheres.add(new SearchCriteria(key, operation, value));
        return this;
    }

    protected BasicSpecificationBuilder<TYPE, SPECIFICATION, FILTER> where(String key, SearchOperation operation, Object value, Object param) {
        wheres.add(new SearchCriteria(key, operation, value, param));
        return this;
    }

    protected BasicSpecificationBuilder<TYPE, SPECIFICATION, FILTER> where(String key, SearchOperation operation) {
        wheres.add(new SearchCriteria(key, operation));
        return this;
    }

    protected abstract void initParams(FILTER filter);

    protected abstract SPECIFICATION buildSpecification(String key, SearchOperation operation, Object value);

    protected abstract SPECIFICATION buildSpecification(String key, SearchOperation operation, Object value, Object param);

    protected SPECIFICATION defaultSpecification() {
        final FetchDeletedEnum showDeleted = showDeleted();
        switch(showDeleted != null ? showDeleted : FetchDeletedEnum.FETCH_NOT_DELETED) {
            case FETCH_DELETED:
                return buildSpecification("deleted", SearchOperation.EQUAL, Boolean.TRUE);
            case FETCH_NOT_DELETED:
                return buildSpecification("deleted", SearchOperation.EQUAL, Boolean.FALSE);
            default:
                return buildSpecification("deleted", SearchOperation.IS_NOT_NULL, null);
        }
    }

    public Optional<Specification<TYPE>> build(FILTER filter) {
        Optional<Specification<TYPE>> result = buildBasicSpecification(filter);

        if (Validation.isNotEmpty(secondaryWiths)) {
            if (result.isPresent()) {
                Specification<TYPE> spec = result.get();

                List<SPECIFICATION> listWiths = secondaryWiths.stream()
                        .map(it -> buildSpecification(it.getKey(), it.getOperation(), it.getValue(), it.getParam()))
                        .collect(Collectors.toList());

                Specification<TYPE> filtersWiths = Specification.where(listWiths.get(0));
                for (int i = 1; i < listWiths.size(); i++) {
                    SPECIFICATION specification = listWiths.get(i);
                    filtersWiths = filtersWiths.or(specification);
                }

                return Optional.of(spec.and(filtersWiths));
            }
        }

        return result;
    }

    private Optional<Specification<TYPE>> buildBasicSpecification(FILTER filter) {
        initParams(filter);

        Specification<TYPE> defaultSpecification = defaultSpecification();

        List<SPECIFICATION> listWiths = withs.stream()
                .map(it -> buildSpecification(it.getKey(), it.getOperation(), it.getValue(), it.getParam()))
                .collect(Collectors.toList());

        if (listWiths.isEmpty() && Validation.isNotEmpty(wheres)) {

            SearchCriteria first = wheres.get(0);
            Specification<TYPE> filters = Specification.where(buildSpecification(first.getKey(), first.getOperation(), first.getValue(), first.getParam()));

            for (int i = 1; i < wheres.size(); i++) {
                SearchCriteria search = wheres.get(i);
                SPECIFICATION specification = buildSpecification(search.getKey(), search.getOperation(), search.getValue(), search.getParam());
                filters = filters.and(specification);
            }

            return Optional.of(defaultSpecification.and(filters));
        }

        if (listWiths.isEmpty()) {
            return Optional.ofNullable(defaultSpecification);
        }

        Specification<TYPE> filtersWiths = Specification.where(listWiths.get(0));
        for (int i = 1; i < listWiths.size(); i++) {
            SPECIFICATION specification = listWiths.get(i);
            filtersWiths = softFilters() ? filtersWiths.or(specification) : filtersWiths.and(specification);
        }

        if (Validation.isNotEmpty(wheres)) {
            for (SearchCriteria it : wheres) {
                filtersWiths = filtersWiths.and(buildSpecification(it.getKey(), it.getOperation(), it.getValue(), it.getParam()));
            }
        }

        return Optional.of(Specification.where(defaultSpecification).and(filtersWiths));
    }

    protected boolean softFilters() {
        return true;
    }

    protected FetchDeletedEnum showDeleted() {
        return FetchDeletedEnum.FETCH_NOT_DELETED;
    }
}