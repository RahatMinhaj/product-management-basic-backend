package com.minhaz.productmanagement.controller;


import com.minhaz.productmanagement.component.route.ApiProvider;
import com.minhaz.productmanagement.dto.ItemDto;
import com.minhaz.productmanagement.entity.Item;
import com.minhaz.productmanagement.param.ItemParam;
import com.minhaz.productmanagement.param.PageableParam;
import com.minhaz.productmanagement.service.ItemService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping(ApiProvider.Item.ROOTPATH)
@Slf4j
public class ItemController {
    private final ItemService itemService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<ItemDto>> save(@RequestBody ItemParam param) {
        return ResponseEntity.status(HttpStatus.OK).body(itemService.create(param));
    }


    @GetMapping(value = ApiProvider.Item.ITEM_IDENTIFIER)
    public ResponseEntity<Optional<ItemDto>> findById(@PathVariable("id") Long id) {
        Optional<ItemDto> itemDto = itemService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(itemDto);
    }


    @Parameters({
            @Parameter(name = "name", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
            @Parameter(name = "pageNo", in = ParameterIn.QUERY, schema = @Schema(type = "int", defaultValue = "1")),
            @Parameter(name = "pageSize", in = ParameterIn.QUERY, schema = @Schema(type = "int", defaultValue = "20")),
            @Parameter(name = "sortBy", in = ParameterIn.QUERY, schema = @Schema(type = "string"), example = "createdAt:desc")
    })
    @GetMapping
    public ResponseEntity findAll(
            @And({
                    @Spec(path = "name", params = "name", spec = LikeIgnoreCase.class),
            }) @Schema(hidden = true) Specification<Item> specification, @Schema(hidden = true) PageableParam pageable) {
        if (pageable.isPageable()) {
            return ResponseEntity.status(HttpStatus.OK).body(itemService.getAll(specification,pageable.getPageable()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(itemService.getAll(specification, pageable.getSort()));
    }


    @PutMapping(value = ApiProvider.Item.ITEM_IDENTIFIER)
    public ResponseEntity<Optional<ItemDto>> update(@PathVariable("id") Long id, @RequestBody ItemParam param) {
        param.setItemId(id);
        Optional<ItemDto> itemDto = itemService.update(param);
        return ResponseEntity.status(HttpStatus.OK).body(itemDto);
    }

    @DeleteMapping(value = ApiProvider.Item.ITEM_IDENTIFIER)
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        itemService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Data Deleted Successfully");
    }

}
