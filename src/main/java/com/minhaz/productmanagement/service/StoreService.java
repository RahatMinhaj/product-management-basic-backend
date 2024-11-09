package com.minhaz.productmanagement.service;



import com.minhaz.productmanagement.dto.ItemDto;
import com.minhaz.productmanagement.dto.StoreDto;
import com.minhaz.productmanagement.entity.Item;
import com.minhaz.productmanagement.entity.Store;
import com.minhaz.productmanagement.param.StoreParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;


public interface StoreService {
    Optional<StoreDto> create(StoreParam storeParam);
    Optional<StoreDto> getById(Long id);
    Optional<Store> getEntityById(Long id);
    Optional<StoreDto> update(StoreParam storeParam);
    void delete(Long id);

    List<StoreDto> getAll(Specification<Store> specification, Sort sort);
    Page<StoreDto> getAll(Specification<Store> specification, Pageable pageable);
}
