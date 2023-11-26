package com.yilan.awesome.system.rest;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yilan.awesome.system.service.RoleService;
import com.yilan.awesome.system.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author: yilan0916
 * @since: 2023-11-26
 */
@RestController
@RequestMapping("/system/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Role role) {
        roleService.saveOrUpdate(role);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        roleService.removeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/del/batch")
    public ResponseEntity<?> deleteBatch(@RequestBody List<Integer> ids) {
        roleService.removeByIds(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Role> list = roleService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable Integer id) {
        Role role = roleService.getById(id);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        Page<Role> page = new Page<>(pageNum, pageSize);
        return new ResponseEntity<>(roleService.page(page), HttpStatus.OK);
    }
}

