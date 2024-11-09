package com.minhaz.productmanagement.service.impl;


import com.minhaz.productmanagement.dto.SaleDto;
import com.minhaz.productmanagement.entity.Sale;
import com.minhaz.productmanagement.mapper.SaleMapper;
import com.minhaz.productmanagement.param.SaleParam;
import com.minhaz.productmanagement.repository.SaleRepository;
import com.minhaz.productmanagement.service.ItemService;
import com.minhaz.productmanagement.service.SaleService;
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
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;
    private final ItemService itemService;
    private final StoreService storeService;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, SaleMapper saleMapper, ItemService itemService, StoreService storeService) {
        this.saleRepository = saleRepository;
        this.saleMapper = saleMapper;
        this.itemService = itemService;
        this.storeService = storeService;
    }

    @Override
    public Optional<SaleDto> create(SaleParam saleParam) {
        Sale sale = paramToEntity(saleParam, new Sale());
        return Optional.of(entityToDto(saleRepository.save(sale)));
    }

    @Override
    public Optional<SaleDto> getById(Long id) {
        return Optional.ofNullable(saleRepository.findById(id)
                .map(this::entityToDto)
                .orElseThrow(() -> new RuntimeException("Sale with ID " + id + " not found")));
    }

    @Override
    public Optional<SaleDto> update(SaleParam saleParam) {
        return Optional.ofNullable(saleRepository.findById(saleParam.getSaleId())
                .map(sale -> {
                    paramToEntity(saleParam, sale);
                    return saleRepository.save(sale);
                })
                .map(this::entityToDto)
                .orElseThrow(() -> new RuntimeException("Sale with ID " + saleParam.getSaleId() + " not found")));
    }

    @Override
    public List<SaleDto> getAll() {
        return saleRepository.findAll().stream().map(this::entityToDto).toList();
    }

    @Override
    public Page<SaleDto> getAll(Specification<Sale> specification, Pageable pageable) {
        return saleRepository.findAll(specification, pageable).map(this::entityToDto);
    }

    @Override
    public List<SaleDto> getAll(Specification<Sale> specification, Sort sort) {
        return saleRepository.findAll(specification, sort).stream().map(this::entityToDto).toList();
    }

    @Override
    public void delete(Long id) {
        saleRepository.deleteById(id);
    }

    private SaleDto entityToDto(Sale sale) {
        SaleDto saleDto = saleMapper.entityToDto(sale);
        return saleDto;
    }

    private Sale paramToEntity(SaleParam param, Sale sale) {
        saleMapper.paramToEntity(param, sale);
        Optional.of(param.getItemId()).ifPresent(id -> {
            itemService.getEntityById(id).ifPresent(sale::setItem);
        });
        Optional.of(param.getStoreId()).ifPresent(id -> {
            storeService.getEntityById(id).ifPresent(sale::setStore);
        });
        return sale;
    }

    @Override
    public Optional<Sale> getEntityById(Long id) {
        return saleRepository.findById(id);
    }
}
