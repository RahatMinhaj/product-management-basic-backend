package com.minhaz.productmanagement.mapper;


import com.minhaz.productmanagement.component.ConfigMapper;
import com.minhaz.productmanagement.dto.KeywordDto;
import com.minhaz.productmanagement.entity.Keyword;
import com.minhaz.productmanagement.param.KeywordParam;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = ConfigMapper.class)
public interface KeywordMapper {
    void paramToEntity(KeywordParam param, @MappingTarget Keyword entity);

    KeywordDto entityToDto(Keyword entity);
}