package com.yilan.awesome.system.rest;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yilan.awesome.system.domain.vo.UserQueryCriteria;
import com.yilan.awesome.system.service.UserService;
import com.yilan.awesome.system.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author: yilan0916
 * @since: 2023-11-26
 */
@Api(tags = "系统：用户管理")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation("导出用户数据")
    @GetMapping(value = "/download")
    public void exportUser(HttpServletResponse response, UserQueryCriteria criteria) throws IOException {
        userService.download(userService.queryAll(criteria), response);
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody User user) {
        userService.saveOrUpdate(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        userService.removeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/del/batch")
    public ResponseEntity<?> deleteBatch(@RequestBody List<Integer> ids) {
        userService.removeByIds(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<User> list = userService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable Integer id) {
        User user = userService.getById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        Page<User> page = new Page<>(pageNum, pageSize);
        return new ResponseEntity<>(userService.page(page), HttpStatus.OK);
    }
}

