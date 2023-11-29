package com.makiti.test.currency.models;

import com.makiti.test.modules.commons.UserDateAudit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author franck.keudem@gmail.com
 */

@Entity
@Table(name = "currency_table")
public class Currency extends UserDateAudit {

  @Id
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "currency_generator"
  )
  @SequenceGenerator(
      name = "currency_generator",
      sequenceName = "currency_seq",
      allocationSize = 1
  )
  @Column(name = "id", updatable = false, nullable = false)
  Long id;

  String currencyCode;

  Float conversionRate;
  Boolean defaultCurrency;

  @ManyToOne
  CurrencyRef currencyRef;

  boolean published = true;

  public Currency() {
  }

  public Currency(
      String currencyCode,
      Float conversionRate,
      Boolean defaultCurrency,
      CurrencyRef currencyRef
  ) {
    this.currencyCode = currencyCode;
    this.conversionRate = conversionRate;
    this.defaultCurrency = defaultCurrency;
    this.currencyRef = currencyRef;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null)
      return false;
    if (obj == this)
      return true;
    return Objects.equals(this.getId(), ((Currency) obj).getId());
  }

  public void prepareToDelete() {
    this.published = false;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Float getConversionRate() {
    return this.conversionRate;
  }

  public void setConversionRate(Float conversionRate) {
    this.conversionRate = conversionRate;
  }

  public Boolean isDefaultCurrency() {
    return this.defaultCurrency;
  }

  public void setDefaultCurrency(Boolean defaultCurrency) {
    this.defaultCurrency = defaultCurrency;
  }

  public CurrencyRef getCurrencyRef() {
    return this.currencyRef;
  }

  public void setCurrencyRef(CurrencyRef currencyRef) {
    this.currencyRef = currencyRef;
  }

  public boolean isPublished() {
    return this.published;
  }

  public void setPublished(boolean published) {
    this.published = published;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }


}
