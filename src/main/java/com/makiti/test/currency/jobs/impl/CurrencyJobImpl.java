package com.makiti.test.currency.jobs.impl;

import com.makiti.test.currency.dtos.CurrencyDto;
import com.makiti.test.currency.dtos.CurrencyRefDto;
import com.makiti.test.currency.jobs.CurrencyJob;
import com.makiti.test.currency.jobs.CurrencyRefJob;
import com.makiti.test.currency.models.Currency;
import com.makiti.test.currency.models.CurrencyRef;
import com.makiti.test.currency.repositories.ICurrencyRepository;
import com.makiti.test.modules.exceptions.ResourceAlreadyExistException;
import com.makiti.test.modules.exceptions.ResourceNotFoundException;

import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author franck.keudem@gmail.com
 */

@Component
@AllArgsConstructor
public class CurrencyJobImpl implements CurrencyJob {

  private final ICurrencyRepository currencyRepository;

  private final CurrencyRefJob currencyRefJob;

  @Transactional
  @Override
  public CurrencyDto save(CurrencyDto item) {
    CurrencyRef currencyRef = CurrencyRefDto.getInstance(currencyRefJob.get(item.currencyRef.id));
    Optional<Currency> optionalCurrency = currencyRepository.findByCurrencyRef(currencyRef);
    if (optionalCurrency.isPresent()) {
      throw new ResourceAlreadyExistException("Currency", "currencyRef", optionalCurrency.get().getCurrencyRef().getName());
    }

    Currency itemToSave = new Currency();
    itemToSave.setId(item.id);
    itemToSave.setConversionRate(item.conversionRate);
    itemToSave.setDefaultCurrency(item.isDefaultCurrency);
    itemToSave.setCurrencyCode(currencyRef.getName());
    itemToSave.setCurrencyRef(currencyRef);

    if (item.isDefaultCurrency) {
      // set pass defaultCurrency to not default
      currencyRepository.resetDefaultCurrency();
    }

    Currency itemSave = currencyRepository.save(itemToSave);
    return CurrencyDto.getInstance(itemSave);
  }

  @Transactional
  @Override
  public CurrencyDto update(long id, CurrencyDto item) {
    Currency itemOnDb = currencyRepository
        .findByIdAndPublished(id, true)
        .orElseThrow(() -> new ResourceNotFoundException("Currency", "id", id));

    CurrencyRef currencyRef = CurrencyRefDto.getInstance(currencyRefJob.get(item.currencyRef.id));

    if (item.isDefaultCurrency) {
      // set pass defaultCurrency to not default
      currencyRepository.resetDefaultCurrency();
    }

    itemOnDb.setConversionRate(item.conversionRate);
    itemOnDb.setDefaultCurrency(item.isDefaultCurrency);
    itemOnDb.setCurrencyCode(currencyRef.getName());
    itemOnDb.setCurrencyRef(currencyRef);
    return CurrencyDto.getInstance(currencyRepository.save(itemOnDb));
  }

  @Override
  public CurrencyDto get(long id) {
    Currency item = currencyRepository
        .findByIdAndPublished(id, true)
        .orElseThrow(() -> new ResourceNotFoundException("Currency", "id", id));
    return CurrencyDto.getInstance(item);
  }

  @Override
  public void delete(long id) {
    Currency itemToDelete = currencyRepository
        .findByIdAndPublished(id, true)
        .orElseThrow(() -> new ResourceNotFoundException("Currency", "id", id));
    itemToDelete.prepareToDelete();
    currencyRepository.save(itemToDelete);
  }

  @Transactional
  @Override
  public void initDefaultCurrencyInDB() {
    CurrencyRef defaultCurrencyRef = currencyRefJob.initCurrencyRefInDB();
    if (defaultCurrencyRef != null) {
      //create default currency
      currencyRepository.save(new Currency(defaultCurrencyRef.getName(), 1.0F, true, defaultCurrencyRef));
    }
  }

  @Override
  public Page<CurrencyDto> getAll(Pageable pageable) {
    return currencyRepository.findAll(pageable)
        .map(CurrencyDto::getInstance);
  }
}
