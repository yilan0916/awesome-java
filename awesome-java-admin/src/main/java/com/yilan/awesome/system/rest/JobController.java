package com.yilan.awesome.system.rest;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yilan.awesome.system.service.JobService;
import com.yilan.awesome.system.domain.Job;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 岗位 前端控制器
 * </p>
 *
 * @author: yilan0916
 * @since: 2023-11-26
 */
@RestController
@RequestMapping("/system/job")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Job job) {
        jobService.saveOrUpdate(job);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        jobService.removeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/del/batch")
    public ResponseEntity<?> deleteBatch(@RequestBody List<Integer> ids) {
        jobService.removeByIds(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Job> list = jobService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable Integer id) {
        Job job = jobService.getById(id);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        Page<Job> page = new Page<>(pageNum, pageSize);
        return new ResponseEntity<>(jobService.page(page), HttpStatus.OK);
    }
}

