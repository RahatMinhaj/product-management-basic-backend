package com.minhaz.productmanagement.service;

import com.minhaz.productmanagement.dto.ChargeFailureLogDto;
import com.minhaz.productmanagement.entity.ChargeFailureLog;
import com.minhaz.productmanagement.mapper.ChargeFailureLogMapper;
import com.minhaz.productmanagement.param.ChargeFailureLogParam;
import com.minhaz.productmanagement.repository.ChargeFailureLogRepository;
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
public class ChargeFailureLogService  {

    private final ChargeFailureLogRepository chargeFailureLogRepository;
    private final ChargeFailureLogMapper chargeFailureLogMapper;

    @Autowired
    public ChargeFailureLogService(ChargeFailureLogRepository chargeFailureLogRepository, ChargeFailureLogMapper chargeFailureLogMapper) {
        this.chargeFailureLogRepository = chargeFailureLogRepository;
        this.chargeFailureLogMapper = chargeFailureLogMapper;
    }


    public Optional<ChargeFailureLogDto> create(ChargeFailureLogParam chargeFailureLogParam) {
        ChargeFailureLog chargeFailureLog = paramToEntity(chargeFailureLogParam, new ChargeFailureLog());
        return Optional.of(entityToDto(chargeFailureLogRepository.save(chargeFailureLog)));
    }


    public Optional<ChargeFailureLogDto> getById(Long id) {
        return Optional.ofNullable(chargeFailureLogRepository.findById(id)
                .map(this::entityToDto)
                .orElseThrow(() -> new RuntimeException("ChargeFailureLog with ID " + id + " not found")));
    }


    public List<ChargeFailureLogDto> getAll() {
        return chargeFailureLogRepository.findAll().stream().map(this::entityToDto).toList();
    }


    public Page<ChargeFailureLogDto> getAll(Specification<ChargeFailureLog> specification, Pageable pageable) {
        return chargeFailureLogRepository.findAll(specification,pageable).map(this::entityToDto);
    }


    public List<ChargeFailureLogDto> getAll(Specification<ChargeFailureLog> specification, Sort sort) {
        return chargeFailureLogRepository.findAll(specification,sort).stream().map(this::entityToDto).toList();
    }


    public Optional<ChargeFailureLogDto> update(ChargeFailureLogParam chargeFailureLogParam) {
        return Optional.ofNullable(chargeFailureLogRepository.findById(chargeFailureLogParam.getId())
                .map(ChargeFailureLog -> {
                    paramToEntity(chargeFailureLogParam, ChargeFailureLog);
                    return chargeFailureLogRepository.save(ChargeFailureLog);
                })
                .map(this::entityToDto)
                .orElseThrow(() -> new RuntimeException("ChargeFailureLog with ID " + chargeFailureLogParam.getId() + " not found")));
    }


    public void delete(Long id) {
        chargeFailureLogRepository.deleteById(id);
    }

    private ChargeFailureLogDto entityToDto(ChargeFailureLog chargeFailureLog) {
        ChargeFailureLogDto chargeFailureLogDto = chargeFailureLogMapper.entityToDto(chargeFailureLog);
        return chargeFailureLogDto;
    }

    private ChargeFailureLog paramToEntity(ChargeFailureLogParam param, ChargeFailureLog chargeFailureLog) {
        chargeFailureLogMapper.paramToEntity(param, chargeFailureLog);
        return chargeFailureLog;
    }
}
