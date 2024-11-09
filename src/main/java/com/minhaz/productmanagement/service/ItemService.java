package com.minhaz.productmanagement.service;

import com.minhaz.productmanagement.dto.ItemDto;
import com.minhaz.productmanagement.entity.Item;
import com.minhaz.productmanagement.param.ItemParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;


public interface ItemService {
    Optional<ItemDto> create(ItemParam itemParam);
    Optional<ItemDto> getById(Long id);
    Optional<Item> getEntityById(Long id);
    List<ItemDto> getAll();
    Page<ItemDto> getAll(Specification<Item> specification, Pageable pageable);
    List<ItemDto> getAll(Specification<Item> specification,Sort sort);
    Optional<ItemDto> update(ItemParam itemParam);
    void delete(Long id);

}
