package ${package.Controller};

import org.springframework.web.bind.annotation.RequestMapping;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.yilan.awesome.base.ResponseResult;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
import ${cfg.criteria}.${entity}QueryCriteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;


import java.util.List;

/**
* <p>
* ${table.comment!} 前端控制器
* </p>
*
* @author ${author}
* @since ${date}
*/
@Tag(name = "demo应用：${table.controllerName}")
@Slf4j
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
@RequiredArgsConstructor
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {

    private final ${table.serviceName} ${table.entityPath}Service;

    @Operation(summary = "查询全部接口")
    @GetMapping
    public ResponseResult<List<${entity}>> getAll() {
        return ResponseResult.success(${table.entityPath}Service.getAll());
    }

    @Operation(summary = "根据ID查询接口")
    @GetMapping("/{id}")
    public ResponseResult<${entity}> getById(@PathVariable("id") Long id) {
        return ResponseResult.success(${table.entityPath}Service.getById(id));
    }

    @Operation(summary = "分页查询接口")
    @PostMapping("/page")
    public ResponseResult<IPage<${entity}>> getByPage(@RequestBody ${entity}QueryCriteria criteria) {
        return ResponseResult.success(${table.entityPath}Service.getByPage(criteria));
    }

    @Operation(summary = "新增和修改接口")
    @PostMapping("/save")
    public ResponseResult<${entity}> save(@RequestBody ${entity} ${table.entityPath}) {
        return ResponseResult.success(${table.entityPath}Service.save(${table.entityPath}));
    }

    @Operation(summary = "批量新增和修改接口")
    @PostMapping("/saveBatch")
    public ResponseResult<Boolean> saveBatch(@RequestBody List<${entity}> list) {
        return ResponseResult.success(${table.entityPath}Service.saveBatch(list));
    }


    @Operation(summary = "删除接口")
    @DeleteMapping("/{id}")
    public ResponseResult<Boolean> deleteById(@PathVariable("id") Long id) {
        log.info("删除id:{}", id);
        return ResponseResult.success(${table.entityPath}Service.deleteById(id));
    }


    </#if>

}
</#if>
