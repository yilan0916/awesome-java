package com.yilan.awesome.mp.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yilan.awesome.base.ResponseResult;
import com.yilan.awesome.mp.service.MpTestService;
import com.yilan.awesome.mp.domain.entity.MpTest;
import com.yilan.awesome.mp.domain.criteria.MpTestQueryCriteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.core.metadata.IPage;


import java.util.List;

/**
* <p>
*  前端控制器
* </p>
*
* @author yilan0916
* @since 2024-07-01
*/
@Api(tags = "demo应用：MpTestController")
@Slf4j
@RestController
@RequestMapping("/mp/mpTest")
@RequiredArgsConstructor
public class MpTestController {

    private final MpTestService mpTestService;

    @ApiOperation("查询全部接口")
    @GetMapping
    public ResponseResult<List<MpTest>> getAll() {
        return ResponseResult.success(mpTestService.getAll());
    }

    @ApiOperation("根据ID查询接口")
    @GetMapping("/{id}")
    public ResponseResult<MpTest> getById(@PathVariable("id") Long id) {
        return ResponseResult.success(mpTestService.getById(id));
    }

    @ApiOperation("分页查询接口")
    @PostMapping("/page")
    public ResponseResult<IPage<MpTest>> getByPage(@RequestBody MpTestQueryCriteria criteria) {
        return ResponseResult.success(mpTestService.getByPage(criteria));
    }

    @ApiOperation("新增和修改接口")
    @PostMapping("/save")
    public ResponseResult<MpTest> save(@RequestBody MpTest mpTest) {
        return ResponseResult.success(mpTestService.save(mpTest));
    }

    @ApiOperation("批量新增和修改接口")
    @PostMapping("/saveBatch")
    public ResponseResult<Boolean> saveBatch(@RequestBody List<MpTest> list) {
        return ResponseResult.success(mpTestService.saveBatch(list));
    }


    @ApiOperation("删除接口")
    @DeleteMapping("/{id}")
    public ResponseResult<Boolean> deleteById(@PathVariable("id") Long id) {
        log.info("删除id:{}", id);
        return ResponseResult.success(mpTestService.deleteById(id));
    }



}
