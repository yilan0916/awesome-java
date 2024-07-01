package com.yilan.awesome.mp.convert;

import com.yilan.awesome.mp.domain.entity.MpTest;
import com.yilan.awesome.mp.domain.vo.MpTestVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
* <p>
*  mapStruct转换类
* </p>
*
* @author yilan0916
* @since 2024-07-01
*/
@Mapper
public interface MpTestConvert {

    MpTestConvert INSTANCE = Mappers.getMapper(MpTestConvert.class);

    MpTest convert(MpTestVO vo);


    MpTestVO convert(MpTest entity);


}

