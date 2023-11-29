package com.makiti.test.currency.jobs.config;

import com.makiti.test.currency.jobs.CurrencyJob;
import com.makiti.test.currency.jobs.CurrencyRefJob;
import com.makiti.test.currency.jobs.impl.CurrencyJobImpl;
import com.makiti.test.currency.repositories.ICurrencyRepository;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class CurrencyJobTestConfig {
  @Bean
  public ICurrencyRepository currencyRepository() {
    return Mockito.mock(ICurrencyRepository.class);
  }

  @Bean
  CurrencyRefJob currencyRefJob() {
    return Mockito.mock(CurrencyRefJob.class);
  }

  @Bean
  public CurrencyJob currencyJob() {
    return  new CurrencyJobImpl(currencyRepository(), currencyRefJob());
  }
}
