package com.minhaz.productmanagement.controller;


import com.minhaz.productmanagement.component.route.ApiProvider;
import com.minhaz.productmanagement.dto.ChargeFailureLogDto;
import com.minhaz.productmanagement.entity.ChargeFailureLog;
import com.minhaz.productmanagement.param.PageableParam;
import com.minhaz.productmanagement.param.ChargeFailureLogParam;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.criteria.JoinType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(ApiProvider.Purchase.ROOTPATH)
@Slf4j
public class PurchaseController {
    private final PurchaseService purchaseService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<ChargeFailureLogDto>> save(@RequestBody ChargeFailureLogParam param) {
        return ResponseEntity.status(HttpStatus.OK).body(purchaseService.create(param));
    }


    @GetMapping(value = ApiProvider.Purchase.PURCHASE_IDENTIFIER)
    public ResponseEntity<Optional<ChargeFailureLogDto>> findById(@PathVariable("id") Long id) {
        Optional<ChargeFailureLogDto> purchaseDto = purchaseService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(purchaseDto);
    }


    @Parameters({
            @Parameter(name = "itemId", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
            @Parameter(name = "pageNo", in = ParameterIn.QUERY, schema = @Schema(type = "int", defaultValue = "1")),
            @Parameter(name = "pageSize", in = ParameterIn.QUERY, schema = @Schema(type = "int", defaultValue = "20")),
            @Parameter(name = "sortBy", in = ParameterIn.QUERY, schema = @Schema(type = "string"), example = "createdAt:desc")
    })
    @GetMapping
    public ResponseEntity findAll(
            @Join(path = "item",type = JoinType.LEFT, alias = "i", distinct = false)
            @And({
                    @Spec(path = "s.name", params = "itemId", spec = Equal.class),
            }) @Schema(hidden = true) Specification<ChargeFailureLog> specification, @Schema(hidden = true) PageableParam pageable) {
        if (pageable.isPageable()) {
            return ResponseEntity.status(HttpStatus.OK).body(purchaseService.getAll(specification,pageable.getPageable()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(purchaseService.getAll(specification, pageable.getSort()));
    }

    @PutMapping(value = ApiProvider.Purchase.PURCHASE_IDENTIFIER)
    public ResponseEntity<Optional<ChargeFailureLogDto>> update(@PathVariable("id") Long id, @RequestBody ChargeFailureLogParam param) {
        param.setPurchaseId(id);
        Optional<ChargeFailureLogDto> purchaseDto = purchaseService.update(param);
        return ResponseEntity.status(HttpStatus.OK).body(purchaseDto);
    }

    @DeleteMapping(value = ApiProvider.Purchase.PURCHASE_IDENTIFIER)
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        purchaseService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Data Deleted Successfully");
    }

}
