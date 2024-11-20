package com.minhaz.productmanagement.service;

import com.minhaz.productmanagement.dto.ChargeConfigDto;
import com.minhaz.productmanagement.entity.ChargeConfig;
import com.minhaz.productmanagement.mapper.ChargeConfigMapper;
import com.minhaz.productmanagement.param.ChargeConfigParam;
import com.minhaz.productmanagement.repository.ChargeConfigRepository;
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
public class ChargeConfigService {

    private final ChargeConfigRepository chargeConfigRepository;
    private final ChargeConfigMapper chargeConfigMapper;

    @Autowired
    public ChargeConfigService(ChargeConfigRepository chargeConfigRepository, ChargeConfigMapper chargeConfigMapper) {
        this.chargeConfigRepository = chargeConfigRepository;
        this.chargeConfigMapper = chargeConfigMapper;
    }

    
    public Optional<ChargeConfigDto> create(ChargeConfigParam chargeConfigParam) {
        ChargeConfig chargeConfig = paramToEntity(chargeConfigParam, new ChargeConfig());
        return Optional.of(entityToDto(chargeConfigRepository.save(chargeConfig)));
    }

    
    public Optional<ChargeConfigDto> getById(Long id) {
        return Optional.ofNullable(chargeConfigRepository.findById(id)
                .map(this::entityToDto)
                .orElseThrow(() -> new RuntimeException("chargeConfig with ID " + id + " not found")));
    }

    public List<ChargeConfigDto> getAll() {
        return chargeConfigRepository.findAll().stream().map(this::entityToDto).toList();
    }

    
    public Page<ChargeConfigDto> getAll(Specification<ChargeConfig> specification, Pageable pageable) {
        return chargeConfigRepository.findAll(specification, pageable).map(this::entityToDto);
    }

    
    public List<ChargeConfigDto> getAll(Specification<ChargeConfig> specification, Sort sort) {
        return chargeConfigRepository.findAll(sort).stream().map(this::entityToDto).toList();
    }

    
    public Optional<ChargeConfigDto> update(ChargeConfigParam chargeConfigParam) {
        return Optional.ofNullable(chargeConfigRepository.findById(chargeConfigParam.getId())
                .map(chargeConfig -> {
                    paramToEntity(chargeConfigParam, chargeConfig);
                    return chargeConfigRepository.save(chargeConfig);
                })
                .map(this::entityToDto)
                .orElseThrow(() -> new RuntimeException("chargeConfig with ID " + chargeConfigParam.getId() + " not found")));
    }

    
    public void delete(Long id) {
        chargeConfigRepository.deleteById(id);
    }

    private ChargeConfigDto entityToDto(ChargeConfig chargeConfig) {
        ChargeConfigDto chargeConfigDto = chargeConfigMapper.entityToDto(chargeConfig);
        return chargeConfigDto;
    }

    private ChargeConfig paramToEntity(ChargeConfigParam param, ChargeConfig chargeConfig) {
        chargeConfigMapper.paramToEntity(param, chargeConfig);
        return chargeConfig;
    }

    
    public Optional<ChargeConfig> getEntityById(Long id) {
        return chargeConfigRepository.findById(id);
    }
}
