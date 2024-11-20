package com.minhaz.productmanagement.service;


import com.minhaz.productmanagement.dto.KeywordDto;
import com.minhaz.productmanagement.entity.Keyword;
import com.minhaz.productmanagement.mapper.KeywordMapper;
import com.minhaz.productmanagement.param.KeywordParam;
import com.minhaz.productmanagement.repository.KeywordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class KeywordService{

    private final KeywordRepository keywordRepository;
    private final KeywordMapper keywordMapper;

    @Autowired
    public KeywordService(KeywordRepository keywordRepository, KeywordMapper keywordMapper) {
        this.keywordRepository = keywordRepository;
        this.keywordMapper = keywordMapper;
    }


    public Optional<KeywordDto> create(KeywordParam keywordParam) {
        Keyword keyword = paramToEntity(keywordParam, new Keyword());
        return Optional.of(entityToDto(keywordRepository.save(keyword)));
    }


    public Optional<KeywordDto> getById(Long id) {
        return Optional.ofNullable(keywordRepository.findById(id)
                .map(this::entityToDto)
                .orElseThrow(() -> new RuntimeException("keyword with ID " + id + " not found")));
    }


    public Optional<KeywordDto> update(KeywordParam keywordParam) {
        return Optional.ofNullable(keywordRepository.findById(keywordParam.getStoreId())
                .map(keyword -> {
                    paramToEntity(keywordParam, keyword);
                    return keywordRepository.save(keyword);
                })
                .map(this::entityToDto)
                .orElseThrow(() -> new RuntimeException("keyword with ID " + keywordParam.getId() + " not found")));
    }


    public Page<KeywordDto> getAll(Specification<Keyword> specification, Pageable pageable) {
        return keywordRepository.findAll(specification, pageable).map(this::entityToDto);
    }


    public List<KeywordDto> getAll(Specification<Keyword> specification, Sort sort) {
        return keywordRepository.findAll(sort).stream().map(this::entityToDto).toList();
    }


    public void delete(Long id) {
        keywordRepository.deleteById(id);
    }

    private KeywordDto entityToDto(Keyword keyword) {
        KeywordDto keywordDto = keywordMapper.entityToDto(keyword);
        return keywordDto;
    }

    private Keyword paramToEntity(KeywordParam param, Keyword keyword) {
        keywordMapper.paramToEntity(param, keyword);
        return keyword;
    }


    public Optional<Keyword> getEntityById(Long id) {
        return keywordRepository.findById(id);
    }
}
