package ru.noxly.efs.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class PaginationRequest {

    @Schema(description = "Номер страницы (начиная с 0)", example = "0", defaultValue = "0")
    private Integer page = 0;

    @Schema(description = "Размер страницы (количество записей)", example = "10", defaultValue = "10")
    private Integer size = 10;

    @Schema(description = "Поле сортировки", example = "id", defaultValue = "id")
    private String sortBy = "id";

    @Schema(description = "Направление сортировки", example = "ASC", defaultValue = "ASC")
    private SortDirection sortDirection = SortDirection.ASC;

    public enum SortDirection {
        ASC, DESC
    }

    /**
     * Конвертирует текущий объект в Pageable
     */
    @JsonIgnore
    public Pageable getPageable() {
        return PageRequest.of(
                page != null ? page : 0,
                size != null ? size : 10,
                sortDirection != null && sortDirection.equals(SortDirection.DESC) ?
                        Sort.by(sortBy != null ? sortBy : "id").descending() :
                        Sort.by(sortBy != null ? sortBy : "id").ascending()
        );
    }

    /**
     * Конвертирует `Pageable` в `PaginationRequest`
     */
    public static PaginationRequest fromPageable(Pageable pageable) {
        if (pageable == null) {
            return new PaginationRequest();
        }
        Sort.Order order = pageable.getSort().stream().findFirst().orElse(new Sort.Order(Sort.Direction.ASC, "id"));
        return PaginationRequest.init()
                .setPage(pageable.getPageNumber())
                .setSize(pageable.getPageSize())
                .setSortBy(order.getProperty())
                .setSortDirection(order.getDirection().equals(Sort.Direction.DESC) ? SortDirection.DESC : SortDirection.ASC)
                .build();
    }
}
