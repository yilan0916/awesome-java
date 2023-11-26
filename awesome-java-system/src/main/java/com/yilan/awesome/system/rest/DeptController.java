package com.yilan.awesome.system.rest;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yilan.awesome.system.service.DeptService;
import com.yilan.awesome.system.domain.Dept;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 部门 前端控制器
 * </p>
 *
 * @author: yilan0916
 * @since: 2023-11-26
 */
@RestController
@RequestMapping("/system/dept")
@RequiredArgsConstructor
public class DeptController {

    private final DeptService deptService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Dept dept) {
        deptService.saveOrUpdate(dept);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        deptService.removeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/del/batch")
    public ResponseEntity<?> deleteBatch(@RequestBody List<Integer> ids) {
        deptService.removeByIds(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Dept> list = deptService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable Integer id) {
        Dept dept = deptService.getById(id);
        return new ResponseEntity<>(dept, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        Page<Dept> page = new Page<>(pageNum, pageSize);
        return new ResponseEntity<>(deptService.page(page), HttpStatus.OK);
    }
}

