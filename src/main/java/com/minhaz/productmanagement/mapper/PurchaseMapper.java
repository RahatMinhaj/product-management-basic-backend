package com.minhaz.productmanagement.mapper;


import com.minhaz.productmanagement.component.ConfigMapper;
import com.minhaz.productmanagement.dto.PurchaseDto;
import com.minhaz.productmanagement.entity.Purchase;
import com.minhaz.productmanagement.param.PurchaseParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(config = ConfigMapper.class)
public interface PurchaseMapper {
    void paramToEntity(PurchaseParam param, @MappingTarget Purchase entity);

    @Mappings({
            @Mapping(source = "item", target = "itemDto"),
            @Mapping(source = "store", target = "storeDto")
    })
    PurchaseDto entityToDto(Purchase entity);
}