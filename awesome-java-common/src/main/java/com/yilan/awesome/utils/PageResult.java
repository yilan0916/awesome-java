package com.yilan.awesome.utils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author： yilan0916
 * @date : 2023/11/27
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class PageResult<T> {

    private final List<T> content;

    private final long totalElements;
}
