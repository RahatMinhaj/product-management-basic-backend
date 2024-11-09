package com.minhaz.productmanagement.mapper;


import com.minhaz.productmanagement.component.ConfigMapper;
import com.minhaz.productmanagement.dto.SaleDto;
import com.minhaz.productmanagement.entity.Sale;
import com.minhaz.productmanagement.param.SaleParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(config = ConfigMapper.class)
public interface SaleMapper {
    void paramToEntity(SaleParam param, @MappingTarget Sale entity);

    @Mappings({
            @Mapping(source = "item", target = "itemDto"),
            @Mapping(source = "store", target = "storeDto")
    })
    SaleDto entityToDto(Sale entity);
}