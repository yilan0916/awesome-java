package com.yilan.awesome.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yilan.awesome.domain.criteria.DeptCriteria;
import com.yilan.awesome.domain.vo.DeptVO;
import com.yilan.awesome.service.impl.DeptServiceImpl;

import java.util.List;

/**
 * @author： yilan0916
 * @date: 2024/7/6
 */
public interface DeptService {

    DeptVO getById(Long id);
    List<DeptVO> getAll();
    IPage<DeptVO> page(DeptCriteria criteria);
    DeptVO save(DeptVO deptVO);
    List<DeptVO> saveBatch(List<DeptVO> list);


}
