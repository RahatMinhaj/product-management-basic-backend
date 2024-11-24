package com.minhaz.productmanagement.repository;


import com.minhaz.productmanagement.entity.ChargeConfig;
import com.minhaz.productmanagement.entity.Inbox;
import com.minhaz.productmanagement.repository.core.CustomRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InboxRepository extends CustomRepository<Inbox, Long> {
    Optional<Inbox> findByTransactionId(UUID transactionId);
}
