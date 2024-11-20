package com.minhaz.productmanagement.controller;


import com.minhaz.productmanagement.component.route.ApiProvider;
import com.minhaz.productmanagement.dto.InboxDto;
import com.minhaz.productmanagement.entity.Inbox;
import com.minhaz.productmanagement.param.InboxParam;
import com.minhaz.productmanagement.param.InboxParam;
import com.minhaz.productmanagement.param.PageableParam;
import com.minhaz.productmanagement.service.InboxService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(ApiProvider.InboxLog.ROOTPATH)
@Slf4j
public class InboxController {
    private final InboxService inboxService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<InboxDto>> save(@RequestBody InboxParam param) {
        return ResponseEntity.status(HttpStatus.OK).body(inboxService.create(param));
    }

    @GetMapping(value = ApiProvider.InboxLog.INBOX_IDENTIFIER)
    public ResponseEntity<Optional<InboxDto>> findById(@PathVariable("id") Long id) {
        Optional<InboxDto> storeDto = inboxService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(storeDto);
    }

    @Parameters({
            @Parameter(name = "pageNo", in = ParameterIn.QUERY, schema = @Schema(type = "int", defaultValue = "1")),
            @Parameter(name = "pageSize", in = ParameterIn.QUERY, schema = @Schema(type = "int", defaultValue = "20")),
            @Parameter(name = "sortBy", in = ParameterIn.QUERY, schema = @Schema(type = "string"), example = "createdAt:desc")
    })
    @GetMapping
    public ResponseEntity findAll(
            @And({
                    
            }) @Schema(hidden = true) Specification<Inbox> specification, @Schema(hidden = true) PageableParam pageable) {
        if (pageable.isPageable()) {
            return ResponseEntity.status(HttpStatus.OK).body(inboxService.getAll(specification, pageable.getPageable()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(inboxService.getAll(specification, pageable.getSort()));
    }

    @PutMapping(value = ApiProvider.InboxLog.INBOX_IDENTIFIER)
    public ResponseEntity<Optional<InboxDto>> update(@PathVariable("id") Long id, @RequestBody InboxParam param) {
        param.setTransactionId(id);
        Optional<InboxDto> storeDto = inboxService.update(param);
        return ResponseEntity.status(HttpStatus.OK).body(storeDto);
    }

    @DeleteMapping(value = ApiProvider.InboxLog.INBOX_IDENTIFIER)
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        inboxService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Data Deleted Successfully");
    }

}
