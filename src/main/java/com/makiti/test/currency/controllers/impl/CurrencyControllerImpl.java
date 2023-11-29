package com.makiti.test.currency.controllers.impl;

import com.makiti.test.currency.controllers.CurrencyController;
import com.makiti.test.currency.dtos.CurrencyDto;
import com.makiti.test.currency.jobs.CurrencyJob;
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
public class CurrencyControllerImpl implements CurrencyController {
  private CurrencyJob currencyJob;

  @Override
  public RequestResult<CurrencyDto> save(@Valid @RequestBody CurrencyDto item) {
    return RequestResult.success(currencyJob.save(item));
  }

  @Override
  public RequestResult<CurrencyDto> get(@PathVariable(value = "id") long id) {
    return RequestResult.success(currencyJob.get(id));
  }

  @Override
  public RequestResult<CurrencyDto> update(
      @PathVariable(value = "id") long id,
      @Valid @RequestBody CurrencyDto item
  ) {
    return RequestResult.success(currencyJob.update(id, item));
  }

  @Override
  public RequestResult<Boolean> delete(@PathVariable(value = "id") long id) {
    currencyJob.delete(id);
    return RequestResult.success(true);
  }

  @Override
  public RequestResult<Page<CurrencyDto>> getAll(Pageable pgble) {
    return RequestResult.success(currencyJob.getAll(pgble));
  }
}
