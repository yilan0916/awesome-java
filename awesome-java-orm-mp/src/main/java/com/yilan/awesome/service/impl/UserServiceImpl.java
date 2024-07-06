package com.yilan.awesome.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yilan.awesome.convert.UserConvert;
import com.yilan.awesome.domain.criteria.UserCriteria;
import com.yilan.awesome.domain.entity.Dept;
import com.yilan.awesome.domain.entity.Role;
import com.yilan.awesome.domain.entity.User;
import com.yilan.awesome.domain.entity.UserRoleRelation;
import com.yilan.awesome.domain.vo.UserVO;
import com.yilan.awesome.mapper.DeptMapper;
import com.yilan.awesome.mapper.RoleMapper;
import com.yilan.awesome.mapper.UserMapper;
import com.yilan.awesome.mapper.UserRoleRelationMapper;
import com.yilan.awesome.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @author： yilan0916
 * @date: 2024/6/29
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final DeptMapper deptMapper;
    private final RoleMapper roleMapper;
    private final UserRoleRelationMapper userRoleRelationMapper;
    private final SqlSessionFactory sqlSessionFactory;

    @Override
    public UserVO getById(Long id) {
        // 先查询用户信息
        User user = userMapper.selectById(id);
        // 转化成VO
        UserVO userVO = Optional.ofNullable(user).map(UserConvert.INSTANCE::convert).orElse(null);
        // 从其他表查询信息再封装到VO
        Optional.ofNullable(userVO).ifPresent(this::addDeptInfo);
        Optional.ofNullable(userVO).ifPresent(u -> {
            addDeptInfo(u);
            addRoleInfo(u);
        });
        return userVO;
    }

    /**
     * 补充部门信息
     *
     * @param userVO /
     */
    private void addDeptInfo(UserVO userVO) {
        Dept dept = deptMapper.selectById(userVO.getDeptId());
        Optional.ofNullable(dept).ifPresent(userVO::setDept);
    }

    /**
     * 补充角色信息
     *
     * @param userVO /
     */
    private void addRoleInfo(UserVO userVO) {
        // 查询匹配关系
        List<UserRoleRelation> userRoleRelations = userRoleRelationMapper.selectList(Wrappers.lambdaQuery(UserRoleRelation.class)
                .eq(UserRoleRelation::getUserId, userVO.getId()));
        Set<Long> roleIds = userRoleRelations.stream().map(UserRoleRelation::getRoleId).collect(Collectors.toSet());
        if (roleIds.size() > 0) {
            List<Role> roleList = roleMapper.selectList(Wrappers.lambdaQuery(Role.class).in(Role::getId, roleIds));

//            TableUtils
//            getHashBasedTable(userRoleRelations)

            userVO.setRoles(roleList);
        }
    }

    @Override
    public List<UserVO> getAll() {
        // 先查询用户列表
        List<User> userList = userMapper.selectList(Wrappers.emptyWrapper());
        List<UserVO> userVOList = userList.stream().map(UserConvert.INSTANCE::convert).collect(Collectors.toList());
        if (!userList.isEmpty()) {
            // 此步骤可以有多个
            addDeptInfo(userVOList);
            addRoleInfo(userVOList);
        }
        return userVOList;
    }

    /**
     * 补充部门信息
     *
     * @param userVOList /
     */
    private void addDeptInfo(List<UserVO> userVOList) {
        // 提取用户的ids，方便批量查询
        Set<Long> deptIds = userVOList.stream().map(UserVO::getDeptId).collect(Collectors.toSet());
        // 根据deptId查询部门(查询前，先做非空判断）
        List<Dept> depts = deptMapper.selectList(Wrappers.lambdaQuery(Dept.class).in(Dept::getId, deptIds));
        // 构造映射关系，方便匹配deptId和dept
        Map<Long, Dept> map = depts.stream().collect(Collectors.toMap(Dept::getId, dept -> dept));
        userVOList.forEach(userVO -> userVO.setDept(map.get(userVO.getDeptId())));
    }

    private void addRoleInfo(List<UserVO> userVOList) {
        // 批量查询User的ID
        Set<Long> userIds = userVOList.stream().map(UserVO::getId).collect(Collectors.toSet());
        LambdaQueryWrapper<UserRoleRelation> queryWrapper = Wrappers.lambdaQuery(UserRoleRelation.class).in(UserRoleRelation::getUserId, userIds);
        List<UserRoleRelation> relationList = userRoleRelationMapper.selectList(queryWrapper);
        Set<Long> roleIds = relationList.stream().map(UserRoleRelation::getRoleId).collect(Collectors.toSet());
        if (roleIds.size() > 0) {
            List<Role> roleList = roleMapper.selectList(Wrappers.lambdaQuery(Role.class).in(Role::getId, roleIds));
            /**
             * 分组转换：
             *     源数据：
             *          1 - 1
             *          1 - 2
             *          2 - 1
             *          2 - 3
             *     转换后：
             *          1 - [1,2]
             *          2 - [1,3]
             */
            Map<Long, List<Long>> map = relationList.stream().collect(
                    Collectors.groupingBy(UserRoleRelation::getUserId, Collectors.mapping(UserRoleRelation::getRoleId, Collectors.toList())));

            // 赋值
            userVOList.forEach(userVO -> {
                List<Role> list = roleList.stream().filter(e -> map.get(userVO.getId()) != null &&
                        map.get(userVO.getId()).contains(e.getId())).collect(Collectors.toList());
                // 中间可加一步Role转RoleVO的步骤
                userVO.setRoles(list);
            });
        }
    }

    @Override
    public IPage<UserVO> page(UserCriteria criteria) {
        Page<User> page = new Page<>(criteria.getPageNum(), criteria.getPageSize());
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        String blurry = criteria.getBlurry();
        if (StringUtils.isNoneBlank(blurry)) {
            queryWrapper.like(User::getUsername, blurry)
                    .or().like(User::getPhone, blurry)
                    .or().like(User::getEmail, blurry);

        }
        // 先查询用户信息，再转化成VO
        IPage<UserVO> userVOPage = userMapper.selectPage(page, queryWrapper).convert(UserConvert.INSTANCE::convert);
        if (!userVOPage.getRecords().isEmpty()) {
            addDeptInfo(userVOPage.getRecords());
            addRoleInfo(userVOPage.getRecords());
        }
        return userVOPage;
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
