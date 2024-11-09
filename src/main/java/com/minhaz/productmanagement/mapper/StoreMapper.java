package com.minhaz.productmanagement.mapper;


import com.minhaz.productmanagement.component.ConfigMapper;
import com.minhaz.productmanagement.dto.StoreDto;
import com.minhaz.productmanagement.entity.Store;
import com.minhaz.productmanagement.param.StoreParam;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = ConfigMapper.class)
public interface StoreMapper {
    void paramToEntity(StoreParam param, @MappingTarget Store entity);

    StoreDto entityToDto(Store entity);
}