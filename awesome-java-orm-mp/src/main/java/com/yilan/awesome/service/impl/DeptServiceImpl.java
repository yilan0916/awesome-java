package com.yilan.awesome.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yilan.awesome.convert.DeptConvert;
import com.yilan.awesome.domain.criteria.DeptCriteria;
import com.yilan.awesome.domain.entity.Dept;
import com.yilan.awesome.domain.entity.User;
import com.yilan.awesome.domain.vo.DeptVO;
import com.yilan.awesome.mapper.DeptMapper;
import com.yilan.awesome.mapper.UserMapper;
import com.yilan.awesome.service.DeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author： yilan0916
 * @date: 2024/7/6
 */
@Service
@RequiredArgsConstructor
public class DeptServiceImpl implements DeptService {
    
    private final DeptMapper deptMapper;
    private final UserMapper userMapper;

    @Override
    public DeptVO getById(Long id) {
        Dept dept = deptMapper.selectById(id);
        DeptVO deptVO = Optional.ofNullable(dept).map(DeptConvert.INSTANCE::convert).orElse(null);
        Optional.ofNullable(deptVO).ifPresent(this::addUserInfo);
        return deptVO;
    }
    
    private void addUserInfo(DeptVO deptVO) {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery(User.class).eq(User::getDeptId, deptVO.getId());
        List<User> userList = userMapper.selectList(queryWrapper);
        deptVO.setUserList(userList);
    }

    @Override
    public List<DeptVO> getAll() {
        // 查询全部部门
        List<Dept> deptList = deptMapper.selectList(Wrappers.emptyWrapper());
        List<DeptVO> deptVOList = deptList.stream().map(DeptConvert.INSTANCE::convert).collect(Collectors.toList());
        if (!deptVOList.isEmpty()) {
            addUserInfo(deptVOList);
        }
        return null;
    }
    private void addUserInfo(List<DeptVO> deptVOList) {
        //
        Set<Long> deptIds = deptVOList.stream().map(DeptVO::getId).collect(Collectors.toSet());
        //
        List<User> userList = userMapper.selectList(Wrappers.lambdaQuery(User.class).in(User::getDeptId, deptIds));
        Map<Long, List<User>> map = userList.stream().collect(Collectors.groupingBy(User::getDeptId));
        deptVOList.forEach(deptVO -> deptVO.setUserList(map.get(deptVO.getId())));
    }

    @Override
    public IPage<DeptVO> page(DeptCriteria criteria) {
        Page<Dept> page = new Page<>(criteria.getPageNum(), criteria.getPageSize());
        IPage<DeptVO> deptVOIPage = deptMapper.selectPage(page, Wrappers.emptyWrapper()).convert(DeptConvert.INSTANCE::convert);
        if (deptVOIPage.getRecords().size() > 0) {
            addUserInfo(deptVOIPage.getRecords());
        }
        return null;
    }

    @Override
    public DeptVO save(DeptVO deptVO) {
        return null;
    }

    @Override
    public List<DeptVO> saveBatch(List<DeptVO> list) {
        return null;
    }
}
