package com.pucminas.cbea.global.specification.filter;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Positive;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasePaginatorFilter extends BaseFilter {

	@Schema(required = false, description = "Número da página.", example = "1", defaultValue = "1")
	@Positive(message = "O número da página deve ser positivo.")
	private int pageNumber = 1;

	@Schema(required = false, description = "Tamanho da página.", example = "50", defaultValue = "50")
	@Positive(message = "O tamanho da página deve ser positivo.")
	private int pageSize = 50;

	@Schema(required = false, description = "Campos para ordenação.", type = "List", example = "[\"id\"]")
	private List<String> sortBy;

	@Schema(required = false, description = "Ordenação (crescente ou decrescente).", example = "ASC")
	private String sortDirection;

	@Schema(required = false, description = "Se todos os elementos serão buscados.", example = "false", defaultValue = "false")
	private Boolean all = Boolean.FALSE;

	@Schema(required = false, description = "Se exportará para Xls.", example = "false", defaultValue = "false")
	private Boolean exportToXls = Boolean.FALSE;

	@Hidden
	private List<Sort.Order> sortFields;

	public BasePaginatorFilter(int pageNumber, int pageSize) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.all = false;
	}
}