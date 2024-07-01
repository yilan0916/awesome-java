package com.yilan.awesome.mp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yilan.awesome.mp.domain.entity.MpTest;
import com.yilan.awesome.mp.domain.criteria.MpTestQueryCriteria;

import java.util.List;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yilan0916
 * @since 2024-07-01
 */
public interface MpTestService {

    List<MpTest> getAll();

    MpTest getById(Long id);

    IPage<MpTest> getByPage(MpTestQueryCriteria criteria);

    MpTest save(MpTest mpTest);

    Boolean saveBatch(List<MpTest> list);

    Boolean deleteById(Long id);

}
