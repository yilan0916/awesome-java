package com.yilan.awesome.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yilan.awesome.config.FileProperties;
import com.yilan.awesome.exception.EntityExistException;
import com.yilan.awesome.exception.EntityNotFoundException;
import com.yilan.awesome.system.domain.Job;
import com.yilan.awesome.system.domain.Role;
import com.yilan.awesome.system.domain.User;
import com.yilan.awesome.system.domain.vo.UserQueryCriteria;
import com.yilan.awesome.system.mapper.UserJobMapper;
import com.yilan.awesome.system.mapper.UserMapper;
import com.yilan.awesome.system.mapper.UserRoleMapper;
import com.yilan.awesome.system.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yilan.awesome.utils.FileUtils;
import com.yilan.awesome.utils.PageResult;
import com.yilan.awesome.utils.PageUtils;
import com.yilan.awesome.utils.RedisUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author yilan0916
 * @since 2023-11-26
 */
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "user")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;
    private final UserJobMapper userJobMapper;
    private final UserRoleMapper userRoleMapper;
    private final FileProperties fileProperties;
    private final RedisUtils redisUtils;


    @Override
    public PageResult<User> queryAll(UserQueryCriteria criteria, Page<Object> page) {
        criteria.setOffset(page.offset());
        List<User> users = userMapper.findAll(criteria);
        Long total = userMapper.countAll(criteria);
        return PageUtils.toPage(users, total);
    }

    @Override
    public List<User> queryAll(UserQueryCriteria criteria) {
        return userMapper.findAll(criteria);
    }

    @Override
    @Cacheable(key = "'id:' + #p0")
    @Transactional(rollbackFor = Exception.class)
    public User findById(long id) {
        return getById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(User resources) {
        resources.setDeptId(resources.getDept().getDeptId());
        if (userMapper.findByUsername(resources.getUsername()) != null) {
            throw new EntityExistException(User.class, "username", resources.getUsername());
        }
        if (userMapper.findByEmail(resources.getEmail()) != null) {
            throw new EntityExistException(User.class, "email", resources.getEmail());
        }
        if (userMapper.findByPhone(resources.getPhone()) != null) {
            throw new EntityExistException(User.class, "phone", resources.getPhone());
        }
        save(resources);
        // 保存用户岗位
        userJobMapper.insertData(resources.getUserId(), resources.getJobs());
        // 保存用户角色
        userRoleMapper.insertData(resources.getUserId(), resources.getRoles());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(User resources) {
        User user = getById(resources.getUserId());
        User user1 = userMapper.findByUsername(resources.getUsername());
        User user2 = userMapper.findByEmail(resources.getEmail());
        User user3 = userMapper.findByPhone(resources.getPhone());
        if (user1 != null && !user.getUserId().equals(user1.getUserId())) {
            throw new EntityExistException(User.class, "username", resources.getUsername());
        }
        if (user2 != null && !user.getUserId().equals(user2.getUserId())) {
            throw new EntityExistException(User.class, "email", resources.getEmail());
        }
        if (user3 != null && !user.getUserId().equals(user3.getUserId())) {
            throw new EntityExistException(User.class, "phone", resources.getPhone());
        }
        // 如果用户的角色改变
        if (!resources.getRoles().equals(user.getRoles())) {
            //TODO
        }
        // 修改部门会影响 数据全县
        if (!Objects.equals(resources.getDept(), user.getDept())) {
            //TODO
        }
        // 如果用户被禁用，则清除用户登录信息
        if (!resources.getIsEnabled()) {
            //TODO
        }
        user.setDeptId(resources.getDept().getDeptId());
        user.setUsername(resources.getUsername());
        user.setEmail(resources.getEmail());
        user.setIsEnabled(resources.getIsEnabled());
        user.setRoles(resources.getRoles());
        user.setDept(resources.getDept());
        user.setJobs(resources.getJobs());
        user.setPhone(resources.getPhone());
        user.setNickName(resources.getNickName());
        user.setGender(resources.getGender());
        saveOrUpdate(user);
        // 清除缓存
        delCaches(user.getUserId(), user.getUsername());
        // 更新用户岗位
        userJobMapper.deleteByUserId(resources.getUserId());
        userJobMapper.insertData(resources.getUserId(), resources.getJobs());
        // 更新用户角色
        userRoleMapper.deleteByUserId(resources.getUserId());
        userRoleMapper.insertData(resources.getUserId(), resources.getRoles());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCenter(User resources) {
        User user = getById(resources.getUserId());
        User user1 = userMapper.findByPhone(resources.getPhone());
        if (user1 != null && !user.getUserId().equals(user1.getUserId())) {
            throw new EntityExistException(User.class, "phone", resources.getPhone());
        }
        user.setNickName(resources.getNickName());
        user.setPhone(resources.getPhone());
        user.setGender(resources.getGender());
        saveOrUpdate(user);
        // 清理缓存
        delCaches(user.getUserId(), user.getUsername());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<Long> ids) {
        for (Long id : ids) {
            // 清理缓存
            User user = getById(id);
            delCaches(user.getUserId(), user.getUsername());
        }
        userMapper.deleteBatchIds(ids);
        // 删除用户岗位
        userJobMapper.deleteByUserIds(ids);
        // 删除用户角色
        userRoleMapper.deleteByUserIds(ids);
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public User getLoginData(String username) {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new EntityNotFoundException(User.class, "name", username);
        }
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePass(String username, String encryptPassword) {
        userMapper.updatePass(username, encryptPassword, new Timestamp(System.currentTimeMillis()));
        flushCache(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resetPwd(Set<Long> ids, String pwd) {
        userMapper.resetPwd(ids, pwd);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, String> updateAvatar(MultipartFile file) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateEmail(String username, String email) {
        userMapper.updateEmail(username, email);
        flushCache(username);
    }


    @Override
    public void download(List<User> users, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (User user : users) {
            List<String> roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toList());
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("用户名", user.getUsername());
            map.put("角色", roles);
            map.put("部门", user.getDept().getName());
            map.put("岗位", user.getJobs().stream().map(Job::getName).collect(Collectors.toList()));
            map.put("邮箱", user.getEmail());
            map.put("状态", user.getIsEnabled() ? "启用" : "禁用");
            map.put("手机号码", user.getPhone());
            map.put("修改密码的时间", user.getPwdResetTime());
            map.put("创建日期", user.getCreateTime());
            list.add(map);
        }
        FileUtils.downloadExcel(list, response);
    }

    /**
     * 清理缓存
     *
     * @param id /
     */
    public void delCaches(Long id, String username) {
//        redisUtils.del(CacheKey.USER_ID + id);
        flushCache(username);
    }

    /**
     * 清理 登陆时 用户缓存信息
     *
     * @param username /
     */
    private void flushCache(String username) {
//        userCacheManager.cleanUserCache(username);
    }
}
