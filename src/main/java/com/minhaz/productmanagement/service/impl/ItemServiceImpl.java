package com.minhaz.productmanagement.service.impl;

import com.minhaz.productmanagement.dto.ItemDto;
import com.minhaz.productmanagement.entity.Item;
import com.minhaz.productmanagement.mapper.ItemMapper;
import com.minhaz.productmanagement.param.ItemParam;
import com.minhaz.productmanagement.repository.ItemRepository;
import com.minhaz.productmanagement.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    @Override
    public Optional<ItemDto> create(ItemParam itemParam) {
        Item item = paramToEntity(itemParam, new Item());
        return Optional.of(entityToDto(itemRepository.save(item)));
    }

    @Override
    public Optional<ItemDto> getById(Long id) {
        return Optional.ofNullable(itemRepository.findById(id)
                .map(this::entityToDto)
                .orElseThrow(() -> new RuntimeException("Item with ID " + id + " not found")));
    }

    //        QItem item = QItem.item;
//        String name = "abc";
//        Double price = 30.0;
//
//        // Create a BooleanBuilder to dynamically build the query conditions
//        BooleanBuilder whereClause = new BooleanBuilder();
//
//        // Add filters based on input parameters
//        if (name != null && !name.isEmpty()) {
//            whereClause.and(item.name.containsIgnoreCase(name)); // case-insensitive filter on name
//        }
//
//        if (price != null) {
//            whereClause.and(item.price.eq(price)); // Exact match on price
//        }
    @Override
    public List<ItemDto> getAll() {
        return itemRepository.findAll().stream().map(this::entityToDto).toList();
    }

    @Override
    public Page<ItemDto> getAll(Specification<Item> specification, Pageable pageable) {
        return itemRepository.findAll(specification, pageable).map(this::entityToDto);
    }

    @Override
    public List<ItemDto> getAll(Specification<Item> specification, Sort sort) {
        return itemRepository.findAll(sort).stream().map(this::entityToDto).toList();
    }

    @Override
    public Optional<ItemDto> update(ItemParam itemParam) {
        return Optional.ofNullable(itemRepository.findById(itemParam.getItemId())
                .map(item -> {
                    paramToEntity(itemParam, item);
                    return itemRepository.save(item);
                })
                .map(this::entityToDto)
                .orElseThrow(() -> new RuntimeException("Item with ID " + itemParam.getItemId() + " not found")));
    }

    @Override
    public void delete(Long id) {
        itemRepository.deleteById(id);
    }

    private ItemDto entityToDto(Item item) {
        ItemDto itemDto = itemMapper.entityToDto(item);
        return itemDto;
    }

    private Item paramToEntity(ItemParam param, Item item) {
        itemMapper.paramToEntity(param, item);
        return item;
    }

    @Override
    public Optional<Item> getEntityById(Long id) {
        return itemRepository.findById(id);
    }
}
