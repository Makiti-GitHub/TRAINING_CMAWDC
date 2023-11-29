package com.makiti.test.currency.dtos;

import com.makiti.test.currency.models.Currency;

/**
 * @author franck.keudem@gmail.com
 */

public class CurrencyDto {

  public Long id;
  public Float conversionRate;
  public Boolean isDefaultCurrency;
  public CurrencyRefDto currencyRef;

  public static CurrencyDto getInstance(Currency item) {
    CurrencyDto itemDto = new CurrencyDto();
    itemDto.id = item.getId();
    itemDto.conversionRate = item.getConversionRate();
    itemDto.isDefaultCurrency = item.isDefaultCurrency();
    itemDto.currencyRef = CurrencyRefDto.getInstance(item.getCurrencyRef());

    return itemDto;
  }

  public static Currency getInstance(CurrencyDto itemDto) {
    Currency item = new Currency();
    item.setId(itemDto.id);
    item.setConversionRate(itemDto.conversionRate);
    item.setDefaultCurrency(itemDto.isDefaultCurrency);
    item.setCurrencyRef(CurrencyRefDto.getInstance(itemDto.currencyRef));

    return item;
  }
}
