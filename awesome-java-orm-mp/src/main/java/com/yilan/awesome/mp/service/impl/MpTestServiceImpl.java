package com.yilan.awesome.mp.service.impl;

import com.yilan.awesome.mp.domain.entity.MpTest;
import com.yilan.awesome.mp.mapper.MpTestMapper;
import com.yilan.awesome.mp.domain.criteria.MpTestQueryCriteria;
import com.yilan.awesome.mp.service.MpTestService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yilan0916
 * @since 2024-07-01
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MpTestServiceImpl implements MpTestService {

    private final MpTestMapper mpTestMapper;
    private final SqlSessionFactory sqlSessionFactory;

    @Override
    public List<MpTest> getAll() {
         return mpTestMapper.selectList(null);
    }

    @Override
    public MpTest getById(Long id) {
         return mpTestMapper.selectById(id);
    }

    @Override
    public IPage<MpTest> getByPage(MpTestQueryCriteria criteria) {
        Page<MpTest> page = new Page<>(criteria.getPageNum(), criteria.getPageSize());
        LambdaQueryWrapper<MpTest> queryWrapper = new LambdaQueryWrapper<>();
        // TODO
        return mpTestMapper.selectPage(page, queryWrapper);
    }

    @Override
    public MpTest save(MpTest mpTest) {
        if (Objects.isNull(mpTest.getId())) {
            mpTestMapper.insert(mpTest);
        } else {
            mpTestMapper.updateById(mpTest);
        }
        return mpTest;
    }

    @Override
    public Boolean saveBatch(List<MpTest> list) {
        // 关闭session的自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        try {
            MpTestMapper mapper = sqlSession.getMapper(MpTestMapper.class);
            list.forEach(mpTest -> {
                if (Objects.isNull(mpTest.getId())) {
                mapper.insert(mpTest);
            } else {
                mapper.updateById(mpTest);
            }
            });
            // 提交数据
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            return false;
        } finally {
            sqlSession.close();
        }
        return true;
    }

    @Override
    public Boolean deleteById(Long id) {
        return mpTestMapper.deleteById(id) == 1;
    }

}
