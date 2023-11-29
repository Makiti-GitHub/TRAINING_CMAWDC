package com.makiti.test.currency.repositories;

import com.makiti.test.currency.models.CurrencyRef;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author franck.keudem@gmail.com
 */

public interface ICurrencyRefRepository
  extends
    JpaRepository<CurrencyRef, Long>, JpaSpecificationExecutor<CurrencyRef> {
  Optional<CurrencyRef> findByIdAndPublished(Long id, boolean published);
}
