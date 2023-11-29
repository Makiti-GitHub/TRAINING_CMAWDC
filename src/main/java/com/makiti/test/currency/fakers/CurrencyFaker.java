package com.makiti.test.currency.fakers;

import com.makiti.test.currency.dtos.CurrencyDto;
import com.makiti.test.modules.faker.FakerUtils;

/**
 * @author franck.keudem@gmail.com
 */

public class CurrencyFaker {

  public static CurrencyDto getInitializedCurrency() {
    CurrencyDto itemDto = new CurrencyDto();

    itemDto.conversionRate = 0.7F;
    itemDto.isDefaultCurrency = true;
    itemDto.currencyRef = CurrencyRefFaker.getInitializedCurrencyRef();

    return itemDto;
  }

  public static CurrencyDto getRandomCurrency() {
    CurrencyDto itemDto = new CurrencyDto();
    itemDto.conversionRate =
      Float.parseFloat(FakerUtils.fakeValuesService.numerify("#####.##"));
    itemDto.isDefaultCurrency = FakerUtils.faker.bool().bool();
    itemDto.currencyRef = CurrencyRefFaker.getRandomCurrencyRef();

    return itemDto;
  }
}
