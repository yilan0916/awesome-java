package com.yilan.awesome.rest;

import com.yilan.awesome.base.PageResultVO;
import com.yilan.awesome.base.ResponseResult;
import com.yilan.awesome.domain.criteria.ZooCriteria;
import com.yilan.awesome.domain.entity.Animal;
import com.yilan.awesome.domain.entity.Zoo;
import com.yilan.awesome.service.ZooService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RestfulApi接口 模版类
 *
 * @author： yilan0916
 * @date: 2024/6/27
 */
@Tag(name = "demo应用：ZooController")
@Slf4j
@RestController
@RequestMapping("/zoos")
@RequiredArgsConstructor
public class ZooController {

    private final ZooService zooService;

    @Operation(summary = "查询全部接口")
    @GetMapping
    public ResponseResult<List<Zoo>> getAll() {
        return ResponseResult.success(zooService.getAll());
    }

    @Operation(summary = "根据ID查询接口")
    @GetMapping("/{id}")
    public ResponseResult<Zoo> getById(@PathVariable("id") Long id) {
        return ResponseResult.success(zooService.getById(id));
    }

    @Operation(summary = "分页查询接口")
    @PostMapping("/page")
    public ResponseResult<PageResultVO<Zoo>> getByPage(@RequestBody ZooCriteria criteria) {
        return ResponseResult.success(zooService.getByPage(criteria));
    }

    @Operation(summary = "新增和修改接口")
    @PostMapping("/save")
    public ResponseResult<Zoo> save(@RequestBody Zoo zoo) {
        return ResponseResult.success(zooService.save(zoo));
    }

    @Operation(summary = "批量新增和修改接口")
    @PostMapping("/saveBatch")
    public ResponseResult<List<Zoo>> saveBatch(@RequestBody List<Zoo> list) {
        return ResponseResult.success(zooService.saveBatch(list));
    }

    @Operation(summary = "新增接口")
    @PostMapping
    public ResponseResult<Zoo> create(@RequestBody Zoo zoo) {
        return ResponseResult.success(zooService.create(zoo));
    }

    @Operation(summary = "修改接口")
    @PutMapping
    public ResponseResult<Zoo> update(@RequestBody Zoo zoo) {
        return ResponseResult.success(zooService.update(zoo));
    }

    @Operation(summary = "删除接口")
    @DeleteMapping("/{id}")
    public ResponseResult<Boolean> deleteById(@PathVariable("id") Long id) {
        log.info("删除id:{}", id);
        return ResponseResult.success(zooService.deleteById(id));
    }

    @Operation(summary = "查询某个指定动物园的所有动物")
    @GetMapping("/{zooId}/animals")
    public ResponseResult<List<Animal>> getAllAnimals(@PathVariable("zooId") Long zooId) {
        return ResponseResult.success(zooService.getAllAnimals(zooId));
    }

    @Operation(summary = "删除某个指定动物园的指定动物")
    @DeleteMapping("/{zooId}/animals/{animalId}")
    public ResponseResult<Boolean> deleteAnimal(@PathVariable("zooId") Long zooId, @PathVariable("animalId") Long animalId) {
        log.info("zooId:{}, animalId:{}", zooId, animalId);
        return ResponseResult.success(zooService.deleteAnimal(zooId, animalId));
    }
}
