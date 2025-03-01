package ru.noxly.efs.common;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponse<T> {
    private List<T> content;   // Список объектов
    private int page;          // Текущая страница
    private int size;          // Размер страницы
    private long totalElements; // Всего записей

    public static <T> PageResponse<T> fromPage(org.springframework.data.domain.Page<T> page) {
        return new PageResponse<>(
                page.getContent(),
                page.getPageable().getPageNumber(),
                page.getPageable().getPageSize(),
                page.getTotalElements()
        );
    }
}
