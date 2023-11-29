package com.makiti.test.currency.jobs;

import com.makiti.test.currency.dtos.CurrencyRefDto;
import com.makiti.test.currency.models.CurrencyRef;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CurrencyRefJob {
  public CurrencyRefDto save(CurrencyRefDto item);

  public CurrencyRefDto get(long id);

  public CurrencyRefDto update(long id, CurrencyRefDto item);

  public void delete(long id);

  public CurrencyRef initCurrencyRefInDB();

  public Page<CurrencyRefDto> getAll(Pageable pageable);
}
