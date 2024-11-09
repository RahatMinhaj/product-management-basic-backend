package com.minhaz.productmanagement.controller;


import com.minhaz.productmanagement.component.route.ApiProvider;
import com.minhaz.productmanagement.dto.StoreDto;
import com.minhaz.productmanagement.entity.Store;
import com.minhaz.productmanagement.param.PageableParam;
import com.minhaz.productmanagement.param.StoreParam;
import com.minhaz.productmanagement.service.StoreService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(ApiProvider.Store.ROOTPATH)
@Slf4j
public class StoreController {
    private final StoreService storeService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<StoreDto>> save(@RequestBody StoreParam param) {
        return ResponseEntity.status(HttpStatus.OK).body(storeService.create(param));
    }


    @GetMapping(value = ApiProvider.Store.STORE_IDENTIFIER)
    public ResponseEntity<Optional<StoreDto>> findById(@PathVariable("id") Long id) {
        Optional<StoreDto> storeDto = storeService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(storeDto);
    }

    @Parameters({
            @Parameter(name = "name", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
            @Parameter(name = "price", in = ParameterIn.QUERY, schema = @Schema(type = "int")),
            @Parameter(name = "pageNo", in = ParameterIn.QUERY, schema = @Schema(type = "int", defaultValue = "1")),
            @Parameter(name = "pageSize", in = ParameterIn.QUERY, schema = @Schema(type = "int", defaultValue = "20")),
            @Parameter(name = "sortBy", in = ParameterIn.QUERY, schema = @Schema(type = "string"), example = "createdAt:desc")
    })
    @GetMapping
    public ResponseEntity findAll(
            @And({
                    @Spec(path = "price", params = "price", spec = Equal.class),
                    @Spec(path = "name", params = "name", spec = LikeIgnoreCase.class),
            }) @Schema(hidden = true) Specification<Store> specification, @Schema(hidden = true) PageableParam pageable) {
        if (pageable.isPageable()) {
            return ResponseEntity.status(HttpStatus.OK).body(storeService.getAll(specification, pageable.getPageable()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(storeService.getAll(specification, pageable.getSort()));
    }

    @PutMapping(value = ApiProvider.Store.STORE_IDENTIFIER)
    public ResponseEntity<Optional<StoreDto>> update(@PathVariable("id") Long id, @RequestBody StoreParam param) {
        param.setStoreId(id);
        Optional<StoreDto> storeDto = storeService.update(param);
        return ResponseEntity.status(HttpStatus.OK).body(storeDto);
    }

    @DeleteMapping(value = ApiProvider.Store.STORE_IDENTIFIER)
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        storeService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Data Deleted Successfully");
    }

}
