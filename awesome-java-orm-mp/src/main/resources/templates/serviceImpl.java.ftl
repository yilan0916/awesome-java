package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${cfg.criteria}.${entity}QueryCriteria;
<#if table.serviceInterface>
import ${package.Service}.${table.serviceName};
</#if>
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Slf4j
@Service
@RequiredArgsConstructor
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>()<#if table.serviceInterface>, ${table.serviceName}</#if> {

}
<#else>
public class ${table.serviceImplName} implements ${table.serviceName} {

    private final ${table.mapperName} ${table.entityPath}Mapper;
    private final SqlSessionFactory sqlSessionFactory;

    @Override
    public List<${entity}> getAll() {
         return ${table.entityPath}Mapper.selectList(null);
    }

    @Override
    public ${entity} getById(Long id) {
         return ${table.entityPath}Mapper.selectById(id);
    }

    @Override
    public IPage<${entity}> getByPage(${entity}QueryCriteria criteria) {
        Page<${entity}> page = new Page<>(criteria.getPageNum(), criteria.getPageSize());
        LambdaQueryWrapper<${entity}> queryWrapper = new LambdaQueryWrapper<>();
        // TODO
        return ${table.entityPath}Mapper.selectPage(page, queryWrapper);
    }

    @Override
    public ${entity} save(${entity} ${table.entityPath}) {
        if (Objects.isNull(${table.entityPath}.getId())) {
            ${table.entityPath}Mapper.insert(${table.entityPath});
        } else {
            ${table.entityPath}Mapper.updateById(${table.entityPath});
        }
        return ${table.entityPath};
    }

    @Override
    public Boolean saveBatch(List<${entity}> list) {
        // 关闭session的自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        try {
            ${table.mapperName} mapper = sqlSession.getMapper(${table.mapperName}.class);
            list.forEach(${table.entityPath} -> {
                if (Objects.isNull(${table.entityPath}.getId())) {
                mapper.insert(${table.entityPath});
            } else {
                mapper.updateById(${table.entityPath});
            }
            });
            // 提交数据
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            return false;
        } finally {
            sqlSession.close();
        }
        return true;
    }

    @Override
    public Boolean deleteById(Long id) {
        return ${table.entityPath}Mapper.deleteById(id) == 1;
    }

}
</#if>
