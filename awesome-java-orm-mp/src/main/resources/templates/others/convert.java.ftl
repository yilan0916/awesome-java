package ${cfg.convert};

import ${package.Entity}.${entity};
import ${cfg.vo}.${entity}VO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
* <p>
* ${table.comment!} mapStruct转换类
* </p>
*
* @author ${author}
* @since ${date}
*/
@Mapper
public interface ${entity}Convert {

    ${entity}Convert INSTANCE = Mappers.getMapper(${entity}Convert.class);

    ${entity} convert(${entity}VO vo);


    ${entity}VO convert(${entity} entity);


}

