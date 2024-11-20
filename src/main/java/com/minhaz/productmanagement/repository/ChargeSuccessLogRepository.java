package com.minhaz.productmanagement.repository;

import com.minhaz.productmanagement.entity.ChargeSuccessLog;
import com.minhaz.productmanagement.repository.core.CustomRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChargeSuccessLogRepository extends CustomRepository<ChargeSuccessLog, Long> {
}
