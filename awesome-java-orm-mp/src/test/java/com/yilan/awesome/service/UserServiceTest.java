package com.yilan.awesome.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yilan.awesome.convert.UserConvert;
import com.yilan.awesome.domain.criteria.UserCriteria;
import com.yilan.awesome.domain.entity.User;
import com.yilan.awesome.domain.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author： yilan0916
 * @date: 2024/6/29
 */
@Slf4j
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testMapStruct() {
        User user = new User();
        user.setId(1L);
        user.setUsername("yilan");
        UserVO convert = UserConvert.INSTANCE.convert(user);
        System.out.println(convert);
    }
    @Test
    void getById() {
        UserVO userVO = userService.getById(1L);
        System.out.println(userVO);
    }

    @Test
    void getAll() {
        List<UserVO> all = userService.getAll();
        all.forEach(System.out::println);
    }

    @Test
    void page() {
//        UserCriteria userCriteria = new UserCriteria();
//        userCriteria.setBlurry(null);
//        userCriteria.setPageNum(1);
//        userCriteria.setPageSize(10);
//        IPage<User> page = userService.page(userCriteria);
//        System.out.println("total:" + page.getTotal());
//        System.out.println("page的大小" + page.getRecords().size());
//        System.out.println(page.getRecords());
    }

    @Test
    void save() {
//        User user = User.builder().username("zk6").password("abcd1234").phone("123").email("abccd@qq.com").build();
//        User save = userService.save(user);
//        System.out.println(save);
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void saveBatch() {
//        List<User> list = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            User user = User.builder().username("saveBatch" + i).password("abcd1234").phone("123").email("abccd@qq.com").build();
//            list.add(user);
//        }
//        userService.saveBatch(list);
    }
}