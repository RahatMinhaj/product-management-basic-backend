package com.minhaz.productmanagement.mapper;


import com.minhaz.productmanagement.component.ConfigMapper;
import com.minhaz.productmanagement.dto.InboxDto;
import com.minhaz.productmanagement.entity.Inbox;
import com.minhaz.productmanagement.param.InboxParam;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Map;

@Mapper(config = ConfigMapper.class)
public interface InboxMapper {
    void paramToEntity(InboxParam param, @MappingTarget Inbox entity);
    InboxDto entityToDto(Inbox entity);
}