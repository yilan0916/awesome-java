package com.yilan.awesome.system.service.impl;

import com.yilan.awesome.system.domain.Job;
import com.yilan.awesome.system.mapper.JobMapper;
import com.yilan.awesome.system.service.JobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 岗位 服务实现类
 * </p>
 *
 * @author yilan0916
 * @since 2023-11-26
 */
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements JobService {

}
