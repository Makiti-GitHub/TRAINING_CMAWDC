package com.makiti.test.currency.jobs;

import com.makiti.test.currency.dtos.CurrencyDto;
import com.makiti.test.currency.dtos.CurrencyRefDto;
import com.makiti.test.currency.fakers.CurrencyFaker;
import com.makiti.test.currency.fakers.CurrencyRefFaker;
import com.makiti.test.currency.jobs.config.CurrencyJobTestConfig;
import com.makiti.test.currency.models.Currency;
import com.makiti.test.currency.repositories.ICurrencyRepository;
import com.makiti.test.modules.exceptions.ResourceAlreadyExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;

@SpringJUnitConfig(CurrencyJobTestConfig.class)
public class CurrencyJobTest {
  @Autowired
  CurrencyJob currencyJob;

  @Autowired
  ICurrencyRepository currencyRepository;

  @Autowired
  CurrencyRefJob currencyRefJob;

  @BeforeEach
  public void restoreMock() {
    Mockito.reset(currencyRepository);
    Mockito.reset(currencyRefJob);
  }

  @Test
  public void when_create_currency_throw_error_resource_already_exist() {
    CurrencyDto currencyDto = CurrencyFaker.getInitializedCurrency();
    currencyDto.id = 5L;
    CurrencyRefDto currencyRefDto = CurrencyRefFaker.getInitializedCurrencyRef();
    currencyRefDto.id = 1L;
    currencyDto.currencyRef = currencyRefDto;

    Mockito.when(currencyRefJob.get(currencyRefDto.id)).thenReturn(currencyRefDto);
    Mockito.when(currencyRepository.findByCurrencyRef(CurrencyRefDto.getInstance(currencyRefDto))).thenReturn(Optional.of(CurrencyDto.getInstance(currencyDto)));

    Assertions.assertThrows(ResourceAlreadyExistException.class, () -> currencyJob.save(currencyDto));
  }

  @Test
  public void when_create_currency_successfully() {
    CurrencyDto currencyDto = CurrencyFaker.getInitializedCurrency();
    currencyDto.id = 5L;
    currencyDto.isDefaultCurrency = false;
    CurrencyRefDto currencyRefDto = CurrencyRefFaker.getInitializedCurrencyRef();
    currencyRefDto.id = 1L;
    currencyDto.currencyRef = currencyRefDto;

    Currency currency = CurrencyDto.getInstance(currencyDto);
    currency.setCurrencyCode(currencyRefDto.name);
    currency.setCurrencyRef(CurrencyRefDto.getInstance(currencyRefDto));

    Mockito.when(currencyRefJob.get(currencyRefDto.id)).thenReturn(currencyRefDto);
    Mockito.when(currencyRepository.findByCurrencyRef(CurrencyRefDto.getInstance(currencyRefDto))).thenReturn(Optional.empty());
    Mockito.when(currencyRepository.save(currency)).thenReturn(currency);


    CurrencyDto currencyDto1 = currencyJob.save(currencyDto);

    Assertions.assertEquals(5L, currencyDto1.id);
  }
}
