package com.minhaz.productmanagement.repository;

import com.minhaz.productmanagement.entity.ChargeFailureLog;
import com.minhaz.productmanagement.repository.core.CustomRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargeFailureLogRepository extends CustomRepository<ChargeFailureLog, Long> {
}
