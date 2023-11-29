package com.makiti.test.currency.models;

import com.makiti.test.currency.dtos.CurrencyDto;
import com.makiti.test.currency.fakers.CurrencyFaker;
import com.makiti.test.currency.repositories.ICurrencyRepository;
import com.makiti.test.modules.exceptions.ResourceNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author franck.keudem@gmail.com
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles(profiles = "test")
public class CurrencyDataJpaTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private ICurrencyRepository currencyRepository;

  @Test
  public void whenSaveCurrency() {
    Currency currency = CurrencyDto.getInstance(
      CurrencyFaker.getInitializedCurrency()
    );

    currency.setCurrencyRef(
      entityManager.persistAndFlush(currency.getCurrencyRef())
    );
    Currency currencySave = entityManager.persistAndFlush(currency);

    Currency currencyFound = currencyRepository
      .findById(currencySave.getId())
      .orElseThrow(() ->
        new ResourceNotFoundException("Notation", "id", currencySave.getId())
      );

    Assertions.assertThat(currencyFound.getConversionRate()).isEqualTo(0.7F);

    Assertions.assertThat(currencyFound.isDefaultCurrency()).isEqualTo(true);
    Assertions.assertThatObject(currencyFound.getCurrencyRef()).isNotNull();
  }
}
