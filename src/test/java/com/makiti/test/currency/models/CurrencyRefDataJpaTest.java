package com.makiti.test.currency.models;

import com.makiti.test.currency.dtos.CurrencyRefDto;
import com.makiti.test.currency.fakers.CurrencyRefFaker;
import com.makiti.test.currency.repositories.ICurrencyRefRepository;
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
public class CurrencyRefDataJpaTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private ICurrencyRefRepository currencyRefRepository;

  @Test
  public void whenSaveCurrencyRef() {
    CurrencyRef currencyRef = CurrencyRefDto.getInstance(
      CurrencyRefFaker.getInitializedCurrencyRef()
    );

    CurrencyRef currencyRefSave = entityManager.persistAndFlush(currencyRef);

    CurrencyRef currencyRefFound = currencyRefRepository
      .findById(currencyRefSave.getId())
      .orElseThrow(() ->
        new ResourceNotFoundException("Notation", "id", currencyRefSave.getId())
      );

    Assertions.assertThat(currencyRefFound.getName()).isEqualTo("XAF");
    Assertions.assertThat(currencyRefFound.getDescription()).isEqualTo("FCA Central africa");
  }
}
