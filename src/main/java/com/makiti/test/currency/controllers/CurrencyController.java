package com.makiti.test.currency.controllers;

import com.makiti.test.currency.dtos.CurrencyDto;
import com.makiti.test.modules.commons.RequestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "System configuration", value = "API to manage currency")
@RestController
@RequestMapping("/api/currency")
public interface CurrencyController {
  @PostMapping("/")
  @ApiOperation(value = "Create new currency")
  public RequestResult<CurrencyDto> save(@Valid @RequestBody CurrencyDto item);

  @GetMapping("/{id}")
  @ApiOperation(value = "Get a specific currency")
  public RequestResult<CurrencyDto> get(@PathVariable(value = "id") long id);

  @PutMapping("/{id}")
  @ApiOperation(value = "Update a currency")
  public RequestResult<CurrencyDto> update(
      @PathVariable(value = "id") long id,
      @Valid @RequestBody CurrencyDto item
  );

  @DeleteMapping("/{id}")
  @ApiOperation(value = "Delete a currency")
  public RequestResult<Boolean> delete(@PathVariable(value = "id") long id);

  @GetMapping("/")
  @ApiOperation(value = "Get all currencies")
  public RequestResult<Page<CurrencyDto>> getAll(Pageable pgble);
}
