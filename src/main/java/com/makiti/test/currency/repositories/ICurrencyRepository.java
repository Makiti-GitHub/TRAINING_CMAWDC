package com.makiti.test.currency.repositories;

import com.makiti.test.currency.models.Currency;

import java.util.Optional;

import com.makiti.test.currency.models.CurrencyRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author franck.keudem@gmail.com
 */

public interface ICurrencyRepository
  extends JpaRepository<Currency, Long>, JpaSpecificationExecutor<Currency> {
  Optional<Currency> findByIdAndPublished(Long id, boolean published);

  Optional<Currency> findByCurrencyCode(String currencyCode);

  Optional<Currency> findByDefaultCurrency(Boolean defaultCurrency);

  Optional<Currency> findByDefaultCurrency(boolean defaultCurrency);

  Optional<Currency> findByCurrencyRef(CurrencyRef currencyRef);

  @Modifying
  @Query("update Currency c set c.defaultCurrency = false")
  void resetDefaultCurrency();
}
