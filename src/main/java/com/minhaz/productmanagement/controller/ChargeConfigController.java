package com.minhaz.productmanagement.controller;


import com.minhaz.productmanagement.component.route.ApiProvider;
import com.minhaz.productmanagement.dto.ChargeConfigDto;
import com.minhaz.productmanagement.entity.ChargeConfig;
import com.minhaz.productmanagement.param.ChargeConfigParam;
import com.minhaz.productmanagement.param.PageableParam;
import com.minhaz.productmanagement.service.ChargeConfigService;
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
@RequestMapping(ApiProvider.ChargeConfig.ROOTPATH)
@Slf4j
public class ChargeConfigController {
    private final ChargeConfigService chargeConfigService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<ChargeConfigDto>> save(@RequestBody ChargeConfigParam param) {
        return ResponseEntity.status(HttpStatus.OK).body(chargeConfigService.create(param));
    }


    @GetMapping(value = ApiProvider.ChargeConfig.CHARGE_CONFIG_IDENTIFIER)
    public ResponseEntity<Optional<ChargeConfigDto>> findById(@PathVariable("id") Long id) {
        Optional<ChargeConfigDto> itemDto = chargeConfigService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(itemDto);
    }


    @Parameters({

            @Parameter(name = "pageNo", in = ParameterIn.QUERY, schema = @Schema(type = "int", defaultValue = "1")),
            @Parameter(name = "pageSize", in = ParameterIn.QUERY, schema = @Schema(type = "int", defaultValue = "20")),
            @Parameter(name = "sortBy", in = ParameterIn.QUERY, schema = @Schema(type = "string"), example = "createdAt:desc")
    })
    @GetMapping
    public ResponseEntity findAll(
            @And({

            }) @Schema(hidden = true) Specification<ChargeConfig> specification, @Schema(hidden = true) PageableParam pageable) {
        if (pageable.isPageable()) {
            return ResponseEntity.status(HttpStatus.OK).body(chargeConfigService.getAll(specification,pageable.getPageable()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(chargeConfigService.getAll(specification, pageable.getSort()));
    }


    @PutMapping(value = ApiProvider.ChargeConfig.CHARGE_CONFIG_IDENTIFIER)
    public ResponseEntity<Optional<ChargeConfigDto>> update(@PathVariable("id") Long id, @RequestBody ChargeConfigParam param) {
        param.setId(id);
        Optional<ChargeConfigDto> itemDto = chargeConfigService.update(param);
        return ResponseEntity.status(HttpStatus.OK).body(itemDto);
    }

    @DeleteMapping(value = ApiProvider.ChargeConfig.CHARGE_CONFIG_IDENTIFIER)
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        chargeConfigService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Data Deleted Successfully");
    }

}
