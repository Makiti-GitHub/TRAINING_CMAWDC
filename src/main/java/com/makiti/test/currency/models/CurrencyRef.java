package com.makiti.test.currency.models;

import com.makiti.test.modules.commons.UserDateAudit;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author franck.keudem@gmail.com
 */

@Entity
@Table(name = "currency_ref_table")
public class CurrencyRef extends UserDateAudit {

  @Id
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "currency_ref_generator"
  )
  @SequenceGenerator(
    name = "currency_ref_generator",
    sequenceName = "currency_ref_seq",
    allocationSize = 1
  )
  @Column(name = "id", updatable = false, nullable = false)
  Long id;

  String name;

  String description;

  @OneToMany(mappedBy = "currencyRef", fetch = FetchType.LAZY)
  Set<Currency> currencies = new HashSet<>();

  boolean published = true;

  public CurrencyRef() {
  }

  public CurrencyRef(String name, String description) {
    this.name = name;
    this.description = description;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null)
      return false;
    if (obj == this)
      return true;
    return Objects.equals(this.getId(), ((CurrencyRef) obj).getId());
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

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Set<Currency> getCurrencies() {
    return this.currencies;
  }

  public void setCurrencies(Set<Currency> currencies) {
    this.currencies = currencies;
  }

  public boolean isPublished() {
    return this.published;
  }

  public void setPublished(boolean published) {
    this.published = published;
  }
}
