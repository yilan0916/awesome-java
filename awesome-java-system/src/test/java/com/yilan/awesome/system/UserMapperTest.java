package com.yilan.awesome.system;

import com.yilan.awesome.system.domain.User;
import com.yilan.awesome.system.domain.vo.UserQueryCriteria;
import com.yilan.awesome.system.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author： yilan0916
 * @date : 2023/11/26
 */
@SpringBootTest
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    void findAllTest() {
        UserQueryCriteria criteria = new UserQueryCriteria();
        List<User> all = userMapper.findAll(criteria);
        for (User user : all) {
            System.out.println(user);
        }

        Long aLong = userMapper.countAll(criteria);
        System.out.println(aLong);
    }

    @Test
    void testEmail() {
        String email = "123@qq.com";
        User byEmail = userMapper.findByEmail(email);
        System.out.println(byEmail);
    }

}
