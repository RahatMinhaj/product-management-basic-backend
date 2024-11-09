package com.minhaz.productmanagement.service.impl;


import com.minhaz.productmanagement.dto.StoreDto;
import com.minhaz.productmanagement.entity.Store;
import com.minhaz.productmanagement.mapper.StoreMapper;
import com.minhaz.productmanagement.param.StoreParam;
import com.minhaz.productmanagement.repository.StoreRepository;
import com.minhaz.productmanagement.service.StoreService;
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
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository, StoreMapper storeMapper) {
        this.storeRepository = storeRepository;
        this.storeMapper = storeMapper;
    }

    @Override
    public Optional<StoreDto> create(StoreParam storeParam) {
        Store store = paramToEntity(storeParam, new Store());
        return Optional.of(entityToDto(storeRepository.save(store)));
    }

    @Override
    public Optional<StoreDto> getById(Long id) {
        return Optional.ofNullable(storeRepository.findById(id)
                .map(this::entityToDto)
                .orElseThrow(() -> new RuntimeException("Store with ID " + id + " not found")));
    }

    @Override
    public Optional<StoreDto> update(StoreParam storeParam) {
        return Optional.ofNullable(storeRepository.findById(storeParam.getStoreId())
                .map(store -> {
                    paramToEntity(storeParam, store);
                    return storeRepository.save(store);
                })
                .map(this::entityToDto)
                .orElseThrow(() -> new RuntimeException("Store with ID " + storeParam.getStoreId() + " not found")));
    }

    @Override
    public Page<StoreDto> getAll(Specification<Store> specification, Pageable pageable) {
        return storeRepository.findAll(specification, pageable).map(this::entityToDto);
    }

    @Override
    public List<StoreDto> getAll(Specification<Store> specification, Sort sort) {
        return storeRepository.findAll(sort).stream().map(this::entityToDto).toList();
    }

    @Override
    public void delete(Long id) {
        storeRepository.deleteById(id);
    }

    private StoreDto entityToDto(Store store) {
        StoreDto storeDto = storeMapper.entityToDto(store);
        return storeDto;
    }

    private Store paramToEntity(StoreParam param, Store store) {
        storeMapper.paramToEntity(param, store);
        return store;
    }

    @Override
    public Optional<Store> getEntityById(Long id) {
        return storeRepository.findById(id);
    }
}
