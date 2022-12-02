package me.youzheng.userservice.mapper;

import me.youzheng.userservice.model.response.CmsResponse;
import me.youzheng.userservice.model.response.HomePageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommonMapper {

    CommonMapper INSTANCE = Mappers.getMapper(CommonMapper.class);
    CmsResponse convertResponse(HomePageResponse response);

}
