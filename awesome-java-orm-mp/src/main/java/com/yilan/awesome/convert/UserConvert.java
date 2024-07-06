package com.yilan.awesome.convert;

import com.yilan.awesome.domain.entity.User;
import com.yilan.awesome.domain.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author： yilan0916
 * @date: 2024/7/5
 */
@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    User convert(UserVO vo);


    UserVO convert(User entity);
}
