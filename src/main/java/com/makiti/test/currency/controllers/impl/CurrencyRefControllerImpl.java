package com.makiti.test.currency.controllers.impl;

import com.makiti.test.currency.controllers.CurrencyRefController;
import com.makiti.test.currency.dtos.CurrencyRefDto;
import com.makiti.test.currency.jobs.CurrencyRefJob;
import com.makiti.test.modules.commons.RequestResult;

import javax.validation.Valid;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author franck.keudem@gmail.com
 */
@Component
@AllArgsConstructor
public class CurrencyRefControllerImpl implements CurrencyRefController {

  private CurrencyRefJob currencyRefJob;

  @Override
  public RequestResult<CurrencyRefDto> save(
      @Valid @RequestBody CurrencyRefDto item
  ) {
    return RequestResult.success(currencyRefJob.save(item));
  }

  @Override
  public RequestResult<CurrencyRefDto> get(
      @PathVariable(value = "id") long id
  ) {
    return RequestResult.success(currencyRefJob.get(id));
  }

  @Override
  public RequestResult<CurrencyRefDto> update(
      @PathVariable(value = "id") long id,
      @Valid @RequestBody CurrencyRefDto item
  ) {
    return RequestResult.success(currencyRefJob.update(id, item));
  }

  @Override
  public RequestResult<Boolean> delete(@PathVariable(value = "id") long id) {
    currencyRefJob.delete(id);
    return RequestResult.success(true);
  }

  @Override
  public RequestResult<Page<CurrencyRefDto>> getAll(Pageable pgble) {
    return RequestResult.success(currencyRefJob.getAll(pgble));
  }
}
