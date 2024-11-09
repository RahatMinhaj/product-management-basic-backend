package com.minhaz.productmanagement.repository;

import com.minhaz.productmanagement.entity.Purchase;
import com.minhaz.productmanagement.repository.core.CustomRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends CustomRepository<Purchase, Long> {
    List<Purchase> findAllByItem_ItemId(Long itemId);
    List<Purchase> findAllByStore_StoreId(Long storeId);
}
