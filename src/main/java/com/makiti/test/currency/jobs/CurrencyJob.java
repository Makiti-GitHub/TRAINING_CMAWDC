package com.makiti.test.currency.jobs;

import com.makiti.test.currency.dtos.CurrencyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CurrencyJob {
  public CurrencyDto save(CurrencyDto item);

  public CurrencyDto update(long id, CurrencyDto item);

  public CurrencyDto get(long id);

  public void delete(long id);

  public void initDefaultCurrencyInDB();

  public Page<CurrencyDto> getAll(Pageable pageable);
}
