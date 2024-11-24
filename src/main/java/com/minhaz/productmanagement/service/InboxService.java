package com.minhaz.productmanagement.service;

import com.minhaz.productmanagement.component.route.ApiProvider;
import com.minhaz.productmanagement.dto.InboxDto;
import com.minhaz.productmanagement.entity.Inbox;
import com.minhaz.productmanagement.mapper.InboxMapper;
import com.minhaz.productmanagement.param.InboxParam;
import com.minhaz.productmanagement.param.ThirdPartyApiResponseDto;
import com.minhaz.productmanagement.repository.InboxRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.threads.VirtualThreadExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
@Slf4j
public class InboxService {

    private final InboxRepository inboxRepository;
    private final InboxMapper inboxMapper;
    private final RestClient restClient;

    @Autowired
    public InboxService(InboxRepository inboxRepository, InboxMapper inboxMapper, RestClient restClient) {
        this.inboxRepository = inboxRepository;
        this.inboxMapper = inboxMapper;
        this.restClient = restClient;
    }


    public Optional<InboxDto> create(InboxParam inboxParam) {
        Inbox inbox = paramToEntity(inboxParam, new Inbox());
        return Optional.of(entityToDto(inboxRepository.save(inbox)));
    }


    public Optional<InboxDto> getById(Long id) {
        return Optional.ofNullable(inboxRepository.findById(id).map(this::entityToDto).orElseThrow(() -> new RuntimeException("Inbox with ID " + id + " not found")));
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
        return Optional.ofNullable(inboxRepository.findById(inboxParam.getId()).map(inbox -> {
            paramToEntity(inboxParam, inbox);
            return inboxRepository.save(inbox);
        }).map(this::entityToDto).orElseThrow(() -> new RuntimeException("Inbox with ID " + inboxParam.getId() + " not found")));
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


    public Optional<Inbox> getAndSaveContentsFromApis() {
        ThirdPartyApiResponseDto body = restClient.get()
                .uri(ApiProvider.ThirdPartyApi.CONTENT_FETCHING_URL)
                .retrieve()
                .body(ThirdPartyApiResponseDto.class);


        assert body != null;
        // Use MapStruct to map raw contents to InboxParam
        Map contents = body.getContents();

        for (Map content: contents.ge){

        }

//        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
//            contents.forEach(content ->  {
//                content.
//                if (inboxRepository.findByTransactionId(content.g).isPresent()) return;
//                try {
//                    create(content);
//                    System.out.println("Saved content: " + content);
//                } catch (Exception e) {
//                    System.err.println("Failed to save content: " + content + ", error: " + e.getMessage());
//                }
//            });
//        }
        return Optional.empty();
    }
}


/*//        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            contents.forEach(content -> executor.submit(() -> {
                if (inboxRepository.findByTransactionId(content.getTransactionId()).isPresent()) return;
                try {
                    create(content);
                    System.out.println("Saved content: " + content);
                } catch (Exception e) {
                    System.err.println("Failed to save content: " + content + ", error: " + e.getMessage());
                }
            }));
//        }
        return Optional.empty();
    }*/