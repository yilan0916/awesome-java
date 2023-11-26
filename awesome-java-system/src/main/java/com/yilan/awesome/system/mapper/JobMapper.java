package com.yilan.awesome.system.mapper;

import com.yilan.awesome.system.domain.Job;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 岗位 Mapper 接口
 * </p>
 *
 * @author yilan0916
 * @since 2023-11-26
 */
@Mapper
public interface JobMapper extends BaseMapper<Job> {

}
