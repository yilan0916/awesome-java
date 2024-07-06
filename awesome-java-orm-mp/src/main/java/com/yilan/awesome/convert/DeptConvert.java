package com.yilan.awesome.convert;

import com.yilan.awesome.domain.entity.Dept;
import com.yilan.awesome.domain.vo.DeptVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author： yilan0916
 * @date: 2024/7/5
 */
@Mapper
public interface DeptConvert {

    DeptConvert INSTANCE = Mappers.getMapper(DeptConvert.class);

    Dept convert(DeptVO vo);

    DeptVO convert(Dept entity);
}
