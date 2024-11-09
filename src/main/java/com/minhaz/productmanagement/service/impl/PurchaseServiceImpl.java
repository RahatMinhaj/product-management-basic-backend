package com.minhaz.productmanagement.service.impl;

import com.minhaz.productmanagement.dto.PurchaseDto;
import com.minhaz.productmanagement.entity.Purchase;
import com.minhaz.productmanagement.mapper.PurchaseMapper;
import com.minhaz.productmanagement.param.PurchaseParam;
import com.minhaz.productmanagement.repository.PurchaseRepository;
import com.minhaz.productmanagement.service.ItemService;
import com.minhaz.productmanagement.service.PurchaseService;
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
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseMapper purchaseMapper;
    private final ItemService itemService;
    private final StoreService storeService;

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, PurchaseMapper purchaseMapper, ItemService itemService, StoreService storeService) {
        this.purchaseRepository = purchaseRepository;
        this.purchaseMapper = purchaseMapper;
        this.itemService = itemService;
        this.storeService = storeService;
    }

    @Override
    public Optional<PurchaseDto> create(PurchaseParam purchaseParam) {
        Purchase purchase = paramToEntity(purchaseParam, new Purchase());
        return Optional.of(entityToDto(purchaseRepository.save(purchase)));
    }

    @Override
    public Optional<PurchaseDto> getById(Long id) {
        return Optional.ofNullable(purchaseRepository.findById(id)
                .map(this::entityToDto)
                .orElseThrow(() -> new RuntimeException("Purchase with ID " + id + " not found")));
    }

    //        QItem purchase = QItem.purchase;
//        String name = "abc";
//        Double price = 30.0;
//
//        // Create a BooleanBuilder to dynamically build the query conditions
//        BooleanBuilder whereClause = new BooleanBuilder();
//
//        // Add filters based on input parameters
//        if (name != null && !name.isEmpty()) {
//            whereClause.and(purchase.name.containsIgnoreCase(name)); // case-insensitive filter on name
//        }
//
//        if (price != null) {
//            whereClause.and(purchase.price.eq(price)); // Exact match on price
//        }
    @Override
    public List<PurchaseDto> getAll() {
        return purchaseRepository.findAll().stream().map(this::entityToDto).toList();
    }

    @Override
    public Page<PurchaseDto> getAll(Specification<Purchase> specification, Pageable pageable) {
        return purchaseRepository.findAll(specification,pageable).map(this::entityToDto);
    }

    @Override
    public List<PurchaseDto> getAll(Specification<Purchase> specification, Sort sort) {
        return purchaseRepository.findAll(specification,sort).stream().map(this::entityToDto).toList();
    }

    @Override
    public Optional<PurchaseDto> update(PurchaseParam purchaseParam) {
        return Optional.ofNullable(purchaseRepository.findById(purchaseParam.getPurchaseId())
                .map(purchase -> {
                    paramToEntity(purchaseParam, purchase);
                    return purchaseRepository.save(purchase);
                })
                .map(this::entityToDto)
                .orElseThrow(() -> new RuntimeException("Purchase with ID " + purchaseParam.getPurchaseId() + " not found")));
    }

    @Override
    public void delete(Long id) {
        purchaseRepository.deleteById(id);
    }

    private PurchaseDto entityToDto(Purchase purchase) {
        PurchaseDto purchaseDto = purchaseMapper.entityToDto(purchase);
        return purchaseDto;
    }

    private Purchase paramToEntity(PurchaseParam param, Purchase purchase) {
        purchaseMapper.paramToEntity(param, purchase);
        Optional.of(param.getItemId()).ifPresent(id ->{
            itemService.getEntityById(id).ifPresent(purchase::setItem);
        });
        Optional.of(param.getStoreId()).ifPresent(id ->{
            storeService.getEntityById(id).ifPresent(purchase::setStore);
        });
        return purchase;
    }
}
