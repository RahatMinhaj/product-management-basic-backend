package com.minhaz.productmanagement.service;

import com.minhaz.productmanagement.dto.ItemDto;
import com.minhaz.productmanagement.dto.PurchaseDto;
import com.minhaz.productmanagement.entity.Item;
import com.minhaz.productmanagement.entity.Purchase;
import com.minhaz.productmanagement.param.ItemParam;
import com.minhaz.productmanagement.param.PurchaseParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;


public interface PurchaseService {
    Optional<PurchaseDto> create(PurchaseParam param);
    Optional<PurchaseDto> getById(Long id);
    List<PurchaseDto> getAll();
    Page<PurchaseDto> getAll(Specification<Purchase> specification, Pageable pageable);
    List<PurchaseDto> getAll(Specification<Purchase> specification,Sort sort);
    Optional<PurchaseDto> update(PurchaseParam param);
    void delete(Long id);

}
