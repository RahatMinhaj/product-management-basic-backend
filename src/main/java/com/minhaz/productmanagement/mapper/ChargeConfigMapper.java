package com.minhaz.productmanagement.mapper;


import com.minhaz.productmanagement.component.ConfigMapper;
import com.minhaz.productmanagement.dto.ChargeConfigDto;
import com.minhaz.productmanagement.entity.ChargeConfig;
import com.minhaz.productmanagement.param.ChargeConfigParam;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = ConfigMapper.class)
public interface ChargeConfigMapper {
    void paramToEntity(ChargeConfigParam param, @MappingTarget ChargeConfig entity);

    ChargeConfigDto entityToDto(ChargeConfig entity);
}