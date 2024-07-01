package com.yilan.awesome.rest;

import com.yilan.awesome.base.PageResultVO;
import com.yilan.awesome.base.ResponseResult;
import com.yilan.awesome.domain.criteria.ZooCriteria;
import com.yilan.awesome.domain.entity.Animal;
import com.yilan.awesome.domain.entity.Zoo;
import com.yilan.awesome.service.ZooService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RestfulApi接口 模版类
 *
 * @author： yilan0916
 * @date: 2024/6/27
 */
@Api(tags = "demo应用：ZooController")
@Slf4j
@RestController
@RequestMapping("/zoos")
@RequiredArgsConstructor
public class ZooController {

    private final ZooService zooService;

    @ApiOperation("查询全部接口")
    @GetMapping
    public ResponseResult<List<Zoo>> getAll() {
        return ResponseResult.success(zooService.getAll());
    }

    @ApiOperation("根据ID查询接口")
    @GetMapping("/{id}")
    public ResponseResult<Zoo> getById(@PathVariable("id") Long id) {
        return ResponseResult.success(zooService.getById(id));
    }

    @ApiOperation("分页查询接口")
    @PostMapping("/page")
    public ResponseResult<PageResultVO<Zoo>> getByPage(@RequestBody ZooCriteria criteria) {
        return ResponseResult.success(zooService.getByPage(criteria));
    }

    @ApiOperation("新增和修改接口")
    @PostMapping("/save")
    public ResponseResult<Zoo> save(@RequestBody Zoo zoo) {
        return ResponseResult.success(zooService.save(zoo));
    }

    @ApiOperation("批量新增和修改接口")
    @PostMapping("/saveBatch")
    public ResponseResult<List<Zoo>> saveBatch(@RequestBody List<Zoo> list) {
        return ResponseResult.success(zooService.saveBatch(list));
    }

    @ApiOperation("新增接口")
    @PostMapping
    public ResponseResult<Zoo> create(@RequestBody Zoo zoo) {
        return ResponseResult.success(zooService.create(zoo));
    }

    @ApiOperation("修改接口")
    @PutMapping
    public ResponseResult<Zoo> update(@RequestBody Zoo zoo) {
        return ResponseResult.success(zooService.update(zoo));
    }

    @ApiOperation("删除接口")
    @DeleteMapping("/{id}")
    public ResponseResult<Boolean> deleteById(@PathVariable("id") Long id) {
        log.info("删除id:{}", id);
        return ResponseResult.success(zooService.deleteById(id));
    }

    @ApiOperation("查询某个指定动物园的所有动物")
    @GetMapping("/{zooId}/animals")
    public ResponseResult<List<Animal>> getAllAnimals(@PathVariable("zooId") Long zooId) {
        return ResponseResult.success(zooService.getAllAnimals(zooId));
    }

    @ApiOperation("删除某个指定动物园的指定动物")
    @DeleteMapping("/{zooId}/animals/{animalId}")
    public ResponseResult<Boolean> deleteAnimal(@PathVariable("zooId") Long zooId, @PathVariable("animalId") Long animalId) {
        log.info("zooId:{}, animalId:{}", zooId, animalId);
        return ResponseResult.success(zooService.deleteAnimal(zooId, animalId));
    }
}
