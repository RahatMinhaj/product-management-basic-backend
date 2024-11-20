package com.minhaz.productmanagement.service;

import com.minhaz.productmanagement.dto.InboxDto;
import com.minhaz.productmanagement.entity.Inbox;
import com.minhaz.productmanagement.mapper.InboxMapper;
import com.minhaz.productmanagement.param.InboxParam;
import com.minhaz.productmanagement.repository.InboxRepository;
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
public class InboxService {

    private final InboxRepository inboxRepository;
    private final InboxMapper inboxMapper;

    @Autowired
    public InboxService(InboxRepository inboxRepository, InboxMapper inboxMapper) {
        this.inboxRepository = inboxRepository;
        this.inboxMapper = inboxMapper;
    }


    public Optional<InboxDto> create(InboxParam inboxParam) {
        Inbox inbox = paramToEntity(inboxParam, new Inbox());
        return Optional.of(entityToDto(inboxRepository.save(inbox)));
    }


    public Optional<InboxDto> getById(Long id) {
        return Optional.ofNullable(inboxRepository.findById(id).map(this::entityToDto).orElseThrow(() -> new RuntimeException("Item with ID " + id + " not found")));
    }


    public List<InboxDto> getAll() {
        return inboxRepository.findAll().stream().map(this::entityToDto).toList();
    }


    public Page<InboxDto> getAll(Specification<Inbox> specification, Pageable pageable) {
        return inboxRepository.findAll(specification, pageable).map(this::entityToDto);
    }


    public List<InboxDto> getAll(Specification<Inbox> specification, Sort sort) {
        return inboxRepository.findAll(sort).stream().map(this::entityToDto).toList();
    }


    public Optional<InboxDto> update(InboxParam inboxParam) {
        return Optional.ofNullable(inboxRepository.findById(inboxParam.getItemId()).map(inbox -> {
            paramToEntity(inboxParam, inbox);
            return inboxRepository.save(inbox);
        }).map(this::entityToDto).orElseThrow(() -> new RuntimeException("Item with ID " + inboxParam.getItemId() + " not found")));
    }

    public void delete(Long id) {
        inboxRepository.deleteById(id);
    }

    private InboxDto entityToDto(Inbox inbox) {
        InboxDto itemDto = inboxMapper.entityToDto(inbox);
        return itemDto;
    }

    private Inbox paramToEntity(InboxParam param, Inbox inbox) {
        inboxMapper.paramToEntity(param, inbox);
        return inbox;
    }

    public Optional<Inbox> getEntityById(Long id) {
        return inboxRepository.findById(id);
    }
}
