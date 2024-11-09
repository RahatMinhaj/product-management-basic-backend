package com.minhaz.productmanagement.mapper;


import com.minhaz.productmanagement.component.ConfigMapper;
import com.minhaz.productmanagement.dto.ItemDto;
import com.minhaz.productmanagement.entity.Item;
import com.minhaz.productmanagement.param.ItemParam;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = ConfigMapper.class)
public interface ItemMapper {
    void paramToEntity(ItemParam param, @MappingTarget Item entity);

    ItemDto entityToDto(Item entity);
}