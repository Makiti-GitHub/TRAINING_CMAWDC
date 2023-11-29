package com.makiti.test.currency.jobs.impl;

import com.makiti.test.currency.dtos.CurrencyRefDto;
import com.makiti.test.currency.jobs.CurrencyRefJob;
import com.makiti.test.currency.models.CurrencyRef;
import com.makiti.test.currency.repositories.ICurrencyRefRepository;
import com.makiti.test.modules.exceptions.ResourceNotFoundException;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * @author franck.keudem@gmail.com
 */


@Component
@AllArgsConstructor
public class CurrencyRefJobImpl implements CurrencyRefJob {

  private final ICurrencyRefRepository currencyRefRepository;

  @Override
  public CurrencyRefDto save(CurrencyRefDto item) {
    CurrencyRef itemToSave = new CurrencyRef();
    itemToSave.setId(item.id);
    itemToSave.setName(item.name);
    itemToSave.setDescription(item.description);
    CurrencyRef itemSave = currencyRefRepository.save(itemToSave);
    return CurrencyRefDto.getInstance(itemSave);
  }

  @Override
  public CurrencyRefDto get(long id) {
    CurrencyRef item = currencyRefRepository
        .findByIdAndPublished(id, true)
        .orElseThrow(() -> new ResourceNotFoundException("CurrencyRef", "id", id)
        );
    return CurrencyRefDto.getInstance(item);
  }

  @Override
  public CurrencyRefDto update(long id, CurrencyRefDto item) {
    CurrencyRef itemOnDb = currencyRefRepository
        .findByIdAndPublished(id, true)
        .orElseThrow(() -> new ResourceNotFoundException("CurrencyRef", "id", id)
        );

    itemOnDb.setName(item.name);
    itemOnDb.setDescription(item.description);
    return CurrencyRefDto.getInstance(currencyRefRepository.save(itemOnDb));
  }

  @Override
  public void delete(long id) {
    CurrencyRef itemToDelete = currencyRefRepository
        .findByIdAndPublished(id, true)
        .orElseThrow(() -> new ResourceNotFoundException("CurrencyRef", "id", id)
        );
    itemToDelete.prepareToDelete();
    currencyRefRepository.save(itemToDelete);
  }

  @Override
  public CurrencyRef initCurrencyRefInDB() {
    if (currencyRefRepository.count() == 0) {
      List<CurrencyRef> currencyRefs = currencyRefRepository.saveAll(Arrays.asList(
          new CurrencyRef("XAF", "CFA Franc BEAC"),
          new CurrencyRef("USD", "United States Dollar"),
          new CurrencyRef("EUR", "Euro"),
          new CurrencyRef("GBP", "British Pound Sterling"),
          new CurrencyRef("JPY", "Japanese Yen")
      ));
      return currencyRefs.stream().filter(c -> c.getName().equals("XAF")).findFirst().orElseThrow(() -> new RuntimeException("USD Currency is not create"));
    }
    return null;
  }

  @Override
  public Page<CurrencyRefDto> getAll(Pageable pageable) {
    return currencyRefRepository.findAll(pageable)
        .map(CurrencyRefDto::getInstance);
  }
}
