package com.minhaz.productmanagement.mapper;


import com.minhaz.productmanagement.component.ConfigMapper;
import com.minhaz.productmanagement.dto.ChargeSuccessLogDto;
import com.minhaz.productmanagement.entity.ChargeSuccessLog;
import com.minhaz.productmanagement.param.ChargeSuccessLogParam;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = ConfigMapper.class)
public interface ChargeSuccessLogMapper {
    void paramToEntity(ChargeSuccessLogParam param, @MappingTarget ChargeSuccessLog entity);

    ChargeSuccessLogDto entityToDto(ChargeSuccessLog entity);
}