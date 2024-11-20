package com.minhaz.productmanagement.controller;


import com.minhaz.productmanagement.component.route.ApiProvider;
import com.minhaz.productmanagement.dto.ChargeSuccessLogDto;
import com.minhaz.productmanagement.entity.ChargeSuccessLog;
import com.minhaz.productmanagement.param.PageableParam;
import com.minhaz.productmanagement.param.ChargeSuccessLogParam;
import com.minhaz.productmanagement.service.ChargeSuccessLogService;
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
@RequestMapping(ApiProvider.ChargeSuccessLog.ROOTPATH)
@Slf4j
public class ChargeSuccessLogController {
    private final ChargeSuccessLogService chargeSuccessLogService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<ChargeSuccessLogDto>> save(@RequestBody ChargeSuccessLogParam param) {
        log.debug("Request to Create Item... {}", param);
        return ResponseEntity.status(HttpStatus.OK).body(chargeSuccessLogService.create(param));
    }


    @GetMapping(value = ApiProvider.ChargeSuccessLog.CHARGE_SUCCESS_LOG_IDENTIFIER)
    public ResponseEntity<Optional<ChargeSuccessLogDto>> findById(@PathVariable("id") Long id) {
        Optional<ChargeSuccessLogDto> saleDto = chargeSuccessLogService.getById(id);
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
            }) @Schema(hidden = true) Specification<ChargeSuccessLog> specification, @Schema(hidden = true) PageableParam pageable) {
        if (pageable.isPageable()) {
            return ResponseEntity.status(HttpStatus.OK).body(chargeSuccessLogService.getAll(specification,pageable.getPageable()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(chargeSuccessLogService.getAll(specification, pageable.getSort()));
    }


    @PutMapping(value = ApiProvider.ChargeSuccessLog.CHARGE_SUCCESS_LOG_IDENTIFIER)
    public ResponseEntity<Optional<ChargeSuccessLogDto>> update(@PathVariable("id") Long id, @RequestBody ChargeSuccessLogParam param) {
        log.debug("Request to Update job-post...{} --> {}", id, param);
        param.setId(id);
        Optional<ChargeSuccessLogDto> saleDto = chargeSuccessLogService.update(param);
        return ResponseEntity.status(HttpStatus.OK).body(saleDto);
    }

    @DeleteMapping(value = ApiProvider.ChargeSuccessLog.CHARGE_SUCCESS_LOG_IDENTIFIER)
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        chargeSuccessLogService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Data Deleted Successfully");
    }

}
