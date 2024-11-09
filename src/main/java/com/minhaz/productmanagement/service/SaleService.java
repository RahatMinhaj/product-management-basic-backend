package com.minhaz.productmanagement.service;


import com.minhaz.productmanagement.dto.ItemDto;
import com.minhaz.productmanagement.dto.SaleDto;
import com.minhaz.productmanagement.entity.Item;
import com.minhaz.productmanagement.entity.Sale;
import com.minhaz.productmanagement.entity.Store;
import com.minhaz.productmanagement.param.SaleParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;


public interface SaleService {
    Optional<SaleDto> create(SaleParam param);

    Optional<SaleDto> getById(Long id);

    Optional<Sale> getEntityById(Long id);

    List<SaleDto> getAll();
    Page<SaleDto> getAll(Specification<Sale> specification, Pageable pageable);
    List<SaleDto> getAll(Specification<Sale> specification,Sort sort);
    Optional<SaleDto> update(SaleParam param);

    void delete(Long id);

}
