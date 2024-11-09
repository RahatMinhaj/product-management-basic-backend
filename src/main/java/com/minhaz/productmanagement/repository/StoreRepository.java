package com.minhaz.productmanagement.repository;

import com.minhaz.productmanagement.entity.Purchase;
import com.minhaz.productmanagement.entity.Store;
import com.minhaz.productmanagement.repository.core.CustomRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StoreRepository extends CustomRepository<Store, Long> {
}
