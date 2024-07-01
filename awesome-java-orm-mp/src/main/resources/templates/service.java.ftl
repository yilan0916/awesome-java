package ${package.Service};

import com.baomidou.mybatisplus.core.metadata.IPage;
import ${package.Entity}.${entity};
import ${cfg.criteria}.${entity}QueryCriteria;

import java.util.List;
/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} {

    List<${entity}> getAll();

    ${entity} getById(Long id);

    IPage<${entity}> getByPage(${entity}QueryCriteria criteria);

    ${entity} save(${entity} ${table.entityPath});

    Boolean saveBatch(List<${entity}> list);

    Boolean deleteById(Long id);

}
</#if>
