package com.minhaz.productmanagement.mapper;


import com.minhaz.productmanagement.component.ConfigMapper;
import com.minhaz.productmanagement.dto.ChargeFailureLogDto;
import com.minhaz.productmanagement.entity.ChargeFailureLog;
import com.minhaz.productmanagement.param.ChargeFailureLogParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(config = ConfigMapper.class)
public interface ChargeFailureLogMapper {
    void paramToEntity(ChargeFailureLogParam param, @MappingTarget ChargeFailureLog entity);

//    @Mappings({
//            @Mapping(source = "item", target = "itemDto"),
//            @Mapping(source = "store", target = "storeDto")
//    })
    ChargeFailureLogDto entityToDto(ChargeFailureLog entity);
}