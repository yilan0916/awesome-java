package com.yilan.awesome.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yilan.awesome.domain.criteria.UserCriteria;
import com.yilan.awesome.domain.entity.User;
import com.yilan.awesome.mapper.UserMapper;
import com.yilan.awesome.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author： yilan0916
 * @date: 2024/6/29
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final SqlSessionFactory sqlSessionFactory;

    @Override
    public User getById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<User> getAll() {
        return userMapper.selectList(null);
    }

    @Override
    public IPage<User> page(UserCriteria criteria) {
        Page<User> page = new Page<>(criteria.getPageNum(), criteria.getPageSize());
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        String blurry = criteria.getBlurry();
        if (StringUtils.isNoneBlank(blurry)) {
            queryWrapper.like(User::getUsername, blurry)
                    .or().like(User::getPhone, blurry)
                    .or().like(User::getEmail, blurry);

        }
        return userMapper.selectPage(page, queryWrapper);
    }

    @Override
    public User save(User user) {
        if (Objects.isNull(user.getId())) {
            userMapper.insert(user);
        } else {
            userMapper.updateById(user);
        }
        return user;
    }

    @Override
    public User create(User user) {
        userMapper.insert(user);
        return user;
    }

    @Override
    public User update(User user) {
        userMapper.updateById(user);
        return user;
    }

    @Override
    public Boolean deleteById(Long id) {
        return userMapper.deleteById(id) == 1;
    }

    @Override
    public Boolean saveBatch(List<User> list) {
        // 关闭session的自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            list.forEach(user -> {
                if (Objects.isNull(user.getId())) {
                    mapper.insert(user);
                } else {
                    mapper.updateById(user);
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
}
