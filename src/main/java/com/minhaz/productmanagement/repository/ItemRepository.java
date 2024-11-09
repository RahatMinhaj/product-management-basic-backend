package com.minhaz.productmanagement.repository;


import com.minhaz.productmanagement.entity.Item;
import com.minhaz.productmanagement.repository.core.CustomRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CustomRepository<Item, Long> {
}
