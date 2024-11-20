package com.minhaz.productmanagement.service;


import com.minhaz.productmanagement.dto.ChargeSuccessLogDto;
import com.minhaz.productmanagement.entity.ChargeSuccessLog;
import com.minhaz.productmanagement.mapper.ChargeSuccessLogMapper;
import com.minhaz.productmanagement.param.ChargeSuccessLogParam;
import com.minhaz.productmanagement.repository.ChargeSuccessLogRepository;
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
public class ChargeSuccessLogService{

    private final ChargeSuccessLogRepository chargeSuccessLogRepository;
    private final ChargeSuccessLogMapper chargeSuccessLogMapper;
    private final ChargeConfigService chargeConfigService;

    @Autowired
    public ChargeSuccessLogService(ChargeSuccessLogRepository chargeSuccessLogRepository, ChargeSuccessLogMapper chargeSuccessLogMapper, ChargeConfigService chargeConfigService) {
        this.chargeSuccessLogRepository = chargeSuccessLogRepository;
        this.chargeSuccessLogMapper = chargeSuccessLogMapper;
        this.chargeConfigService = chargeConfigService;
    }


    public Optional<ChargeSuccessLogDto> create(ChargeSuccessLogParam chargeConfigParam) {
        ChargeSuccessLog chargeSuccessLog = paramToEntity(chargeConfigParam, new ChargeSuccessLog());
        return Optional.of(entityToDto(chargeSuccessLogRepository.save(chargeSuccessLog)));
    }


    public Optional<ChargeSuccessLogDto> getById(Long id) {
        return Optional.ofNullable(chargeSuccessLogRepository.findById(id)
                .map(this::entityToDto)
                .orElseThrow(() -> new RuntimeException("ChargeSuccessLog with ID " + id + " not found")));
    }


    public Optional<ChargeSuccessLogDto> update(ChargeSuccessLogParam chargeConfigParam) {
        return Optional.ofNullable(chargeSuccessLogRepository.findById(chargeConfigParam.getId())
                .map(chargeConfig -> {
                    paramToEntity(chargeConfigParam, chargeConfig);
                    return chargeSuccessLogRepository.save(chargeConfig);
                })
                .map(this::entityToDto)
                .orElseThrow(() -> new RuntimeException("ChargeSuccessLog with ID " + chargeConfigParam.getId() + " not found")));
    }


    public List<ChargeSuccessLogDto> getAll() {
        return chargeSuccessLogRepository.findAll().stream().map(this::entityToDto).toList();
    }


    public Page<ChargeSuccessLogDto> getAll(Specification<ChargeSuccessLog> specification, Pageable pageable) {
        return chargeSuccessLogRepository.findAll(specification, pageable).map(this::entityToDto);
    }


    public List<ChargeSuccessLogDto> getAll(Specification<ChargeSuccessLog> specification, Sort sort) {
        return chargeSuccessLogRepository.findAll(specification, sort).stream().map(this::entityToDto).toList();
    }


    public void delete(Long id) {
        chargeSuccessLogRepository.deleteById(id);
    }

    private ChargeSuccessLogDto entityToDto(ChargeSuccessLog chargeSuccessLog) {
        ChargeSuccessLogDto chargeSuccessLogDto = chargeSuccessLogMapper.entityToDto(chargeSuccessLog);
        return chargeSuccessLogDto;
    }

    private ChargeSuccessLog paramToEntity(ChargeSuccessLogParam param, ChargeSuccessLog chargeSuccessLog) {
        chargeSuccessLogMapper.paramToEntity(param, chargeSuccessLog);
        return chargeSuccessLog;
    }


    public Optional<ChargeSuccessLog> getEntityById(Long id) {
        return chargeSuccessLogRepository.findById(id);
    }
}
