package com.minhaz.productmanagement.repository;


import com.minhaz.productmanagement.entity.ChargeConfig;
import com.minhaz.productmanagement.entity.Inbox;
import com.minhaz.productmanagement.repository.core.CustomRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargeConfigRepository extends CustomRepository<ChargeConfig, Long> {
}
