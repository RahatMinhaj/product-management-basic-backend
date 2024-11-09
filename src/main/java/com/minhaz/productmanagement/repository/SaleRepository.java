package com.minhaz.productmanagement.repository;

import com.minhaz.productmanagement.entity.Sale;
import com.minhaz.productmanagement.repository.core.CustomRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends CustomRepository<Sale, Long> {
    List<Sale> findAllByItem_ItemId(Long itemId);
    List<Sale> findAllByStore_StoreId(Long itemId);
}
