package com.intern.study.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Pagination<T> {
    private final int page;
    private final int size;
    private final Long totalElements;
    private final int totalPage;
    private final List<T> content;

    public static <T> Pagination<T> of(
            int page,
            int size,
            Long totalElements,
            int totalPage,
            List<T> content
    ) {
        return new Pagination<>(page, size, totalElements, totalPage, content);
    }
}
