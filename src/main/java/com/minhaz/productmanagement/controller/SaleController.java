package com.minhaz.productmanagement.controller;


import com.minhaz.productmanagement.component.route.ApiProvider;
import com.minhaz.productmanagement.dto.SaleDto;
import com.minhaz.productmanagement.entity.Item;
import com.minhaz.productmanagement.entity.Sale;
import com.minhaz.productmanagement.param.PageableParam;
import com.minhaz.productmanagement.param.SaleParam;
import com.minhaz.productmanagement.service.SaleService;
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
@RequestMapping(ApiProvider.Sale.ROOTPATH)
@Slf4j
public class SaleController {
    private final SaleService saleService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<SaleDto>> save(@RequestBody SaleParam param) {
        log.debug("Request to Create Item... {}", param);
        return ResponseEntity.status(HttpStatus.OK).body(saleService.create(param));
    }


    @GetMapping(value = ApiProvider.Sale.SALE_IDENTIFIER)
    public ResponseEntity<Optional<SaleDto>> findById(@PathVariable("id") Long id) {
        Optional<SaleDto> saleDto = saleService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(saleDto);
    }


    @Parameters({
            @Parameter(name = "pageNo", in = ParameterIn.QUERY, schema = @Schema(type = "int", defaultValue = "1")),
            @Parameter(name = "pageSize", in = ParameterIn.QUERY, schema = @Schema(type = "int", defaultValue = "20")),
            @Parameter(name = "sortBy", in = ParameterIn.QUERY, schema = @Schema(type = "string"), example = "createdAt:desc")
    })
    @GetMapping
    public ResponseEntity findAll(
            @And({
            }) @Schema(hidden = true) Specification<Sale> specification, @Schema(hidden = true) PageableParam pageable) {
        if (pageable.isPageable()) {
            return ResponseEntity.status(HttpStatus.OK).body(saleService.getAll(specification,pageable.getPageable()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(saleService.getAll(specification, pageable.getSort()));
    }


    @PutMapping(value = ApiProvider.Sale.SALE_IDENTIFIER)
    public ResponseEntity<Optional<SaleDto>> update(@PathVariable("id") Long id, @RequestBody SaleParam param) {
        log.debug("Request to Update job-post...{} --> {}", id, param);
        param.setSaleId(id);
        Optional<SaleDto> saleDto = saleService.update(param);
        return ResponseEntity.status(HttpStatus.OK).body(saleDto);
    }

    @DeleteMapping(value = ApiProvider.Sale.SALE_IDENTIFIER)
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        saleService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Data Deleted Successfully");
    }

}
