package com.yilan.awesome.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yilan.awesome.system.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yilan.awesome.system.domain.vo.UserQueryCriteria;
import com.yilan.awesome.utils.PageResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author yilan0916
 * @since 2023-11-26
 */
public interface UserService extends IService<User> {

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return /
     */
    User findById(long id);

    /**
     * 新增用户
     *
     * @param resources /
     */
    void create(User resources);

    /**
     * 编辑用户
     *
     * @param resources /
     * @throws Exception /
     */
    void update(User resources);

    /**
     * 删除用户
     *
     * @param ids /
     */
    void delete(Set<Long> ids);

    /**
     * 根据用户名查询
     *
     * @param username /
     * @return /
     */
    User findByUsername(String username);

    /**
     * 根据用户名查询
     *
     * @param username /
     * @return /
     */
    User getLoginData(String username);

    /**
     * 修改密码
     *
     * @param username        用户名
     * @param encryptPassword 密码
     */
    void updatePass(String username, String encryptPassword);

    /**
     * 修改头像
     *
     * @param file 文件
     * @return /
     */
    Map<String, String> updateAvatar(MultipartFile file);

    /**
     * 修改邮箱
     *
     * @param username 用户名
     * @param email    邮箱
     */
    void updateEmail(String username, String email);

    /**
     * 查询全部
     *
     * @param criteria 条件
     * @param page     分页参数
     * @return /
     */
    PageResult<User> queryAll(UserQueryCriteria criteria, Page<Object> page);

    /**
     * 查询全部不分页
     *
     * @param criteria 条件
     * @return /
     */
    List<User> queryAll(UserQueryCriteria criteria);

    /**
     * 导出数据
     *
     * @param queryAll 待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<User> queryAll, HttpServletResponse response) throws IOException;

    /**
     * 用户自助修改资料
     *
     * @param resources /
     */
    void updateCenter(User resources);

    /**
     * 重置密码
     *
     * @param ids 用户id
     * @param pwd 密码
     */
    void resetPwd(Set<Long> ids, String pwd);
}
