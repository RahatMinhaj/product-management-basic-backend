package com.minhaz.productmanagement.controller;


import com.minhaz.productmanagement.component.route.ApiProvider;
import com.minhaz.productmanagement.dto.KeywordDto;
import com.minhaz.productmanagement.entity.Keyword;
import com.minhaz.productmanagement.param.PageableParam;
import com.minhaz.productmanagement.param.KeywordParam;
import com.minhaz.productmanagement.service.KeywordService;
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
@RequestMapping(ApiProvider.Keyword.ROOTPATH)
@Slf4j
public class KeywordController {
    private final KeywordService keywordService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<KeywordDto>> save(@RequestBody KeywordParam param) {
        return ResponseEntity.status(HttpStatus.OK).body(keywordService.create(param));
    }


    @GetMapping(value = ApiProvider.Keyword.KEYWORD_IDENTIFIER)
    public ResponseEntity<Optional<KeywordDto>> findById(@PathVariable("id") Long id) {
        Optional<KeywordDto> storeDto = keywordService.getById(id);
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

            }) @Schema(hidden = true) Specification<Keyword> specification, @Schema(hidden = true) PageableParam pageable) {
        if (pageable.isPageable()) {
            return ResponseEntity.status(HttpStatus.OK).body(keywordService.getAll(specification, pageable.getPageable()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(keywordService.getAll(specification, pageable.getSort()));
    }

    @PutMapping(value = ApiProvider.Keyword.KEYWORD_IDENTIFIER)
    public ResponseEntity<Optional<KeywordDto>> update(@PathVariable("id") Long id, @RequestBody KeywordParam param) {
        param.setStoreId(id);
        Optional<KeywordDto> storeDto = keywordService.update(param);
        return ResponseEntity.status(HttpStatus.OK).body(storeDto);
    }

    @DeleteMapping(value = ApiProvider.Keyword.KEYWORD_IDENTIFIER)
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        keywordService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Data Deleted Successfully");
    }

}
