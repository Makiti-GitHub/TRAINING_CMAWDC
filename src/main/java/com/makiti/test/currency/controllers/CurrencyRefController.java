package com.makiti.test.currency.controllers;

import com.makiti.test.currency.dtos.CurrencyRefDto;
import com.makiti.test.modules.commons.RequestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "System configuration", value = "API to manage currency reference")
@RestController
@RequestMapping("/api/currency-ref")
public interface CurrencyRefController {
  @PostMapping("/")
  @ApiOperation(value = "Create new currency ref")
  public RequestResult<CurrencyRefDto> save(@Valid @RequestBody CurrencyRefDto item);

  @GetMapping("/{id}")
  @ApiOperation(value = "Get a specific currency ref")
  public RequestResult<CurrencyRefDto> get(@PathVariable(value = "id") long id);

  @PutMapping("/{id}")
  @ApiOperation(value = "Update a currency ref")
  public RequestResult<CurrencyRefDto> update(
      @PathVariable(value = "id") long id,
      @Valid @RequestBody CurrencyRefDto item
  );

  @DeleteMapping("/{id}")
  @ApiOperation(value = "Delete a currency ref")
  public RequestResult<Boolean> delete(@PathVariable(value = "id") long id);

  @GetMapping("/")
  @ApiOperation(value = "Get all currencies refs")
  public RequestResult<Page<CurrencyRefDto>> getAll(Pageable pgble);
}
