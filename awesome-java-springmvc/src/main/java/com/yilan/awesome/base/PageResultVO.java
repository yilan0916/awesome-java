package com.yilan.awesome.base;

import lombok.Data;

import java.util.List;

/**
 * 自定义分页VO
 * @author： yilan0916
 * @date: 2024/6/27
 */
@Data
public class PageResultVO<T>  {

    private List<T> records;
    private Integer total;
    private Integer size;
    private Integer current;

    /**
     * 禁止空参构造
     */
    private PageResultVO() {
    }

    public static <T> PageResultVO<T> page(List<T> allList, int pageNum, int pageSize) {
        PageResultVO<T> page = new PageResultVO<>();
        page.setTotal(allList.size());
        page.setCurrent(pageNum);
        page.setSize(pageSize);

        int start = (pageNum - 1) * pageSize;
        int end = Math.min(start + pageSize, allList.size());
        if (start < end) {
            List<T> subList = allList.subList(start, end);
            page.setRecords(subList);
        }

        return page;
    }
}