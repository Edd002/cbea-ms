package com.pucminas.cbea.global.specification.builder;

import com.pucminas.cbea.global.specification.enums.SortOrder;
import com.pucminas.cbea.global.specification.filter.BasePaginatorFilter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class PageableBuilder {

	public static final String SUM = "sum";
	public static final String VALUE = "value";

	public Pageable build(BasePaginatorFilter filter) {
		int pageNumber = filter.getPageNumber() - 1;
		int pageSize = filter.getAll() || filter.getExportToXls() ? 1000000: filter.getPageSize();
		//in dto at front this field calls sum
		List<String> sortBy = filter.getSortBy();
		List<Sort.Order> sortFields = (sortBy != null && !sortBy.isEmpty()) ? (sortBy.equals(SUM) ? Arrays.asList(Sort.Order.by(VALUE)) : orderProperties(sortBy)) : new ArrayList<>();
		Pageable pageable;
		SortOrder sortDirection = filter.getSortDirection() == null ? SortOrder.EMPTY
													   : SortOrder.valueOfIgnoreCase(filter.getSortDirection());
		switch (sortDirection) {
			case ASC:
				pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortFields));
				break;
			case DESC:
				pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortFields).descending());
				break;
			case EMPTY:
				pageable = PageRequest.of(pageNumber, pageSize);
				break;
			case BOTH:
				pageable = PageRequest.of(pageNumber, pageSize, Sort.by(filter.getSortFields() != null ? filter.getSortFields() : new ArrayList<>()));
				break;
			default:
				throw new IllegalArgumentException("Wrong sort order");
		}

		return pageable;
	}

	private List<Sort.Order> orderProperties(List<String> properties) {
		List<Sort.Order> sortFields = new ArrayList<>();
		properties.forEach(property -> sortFields.add(Sort.Order.by(property)));

		return sortFields;
	}
}