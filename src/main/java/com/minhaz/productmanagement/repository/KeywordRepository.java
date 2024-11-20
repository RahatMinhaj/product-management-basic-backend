package com.minhaz.productmanagement.repository;

import com.minhaz.productmanagement.entity.Keyword;
import com.minhaz.productmanagement.repository.core.CustomRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface KeywordRepository extends CustomRepository<Keyword, Long> {
}
