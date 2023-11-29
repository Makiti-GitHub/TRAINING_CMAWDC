package com.makiti.test.currency.fakers;

import com.makiti.test.currency.dtos.CurrencyRefDto;
import com.makiti.test.modules.faker.FakerUtils;

/**
 * @author franck.keudem@gmail.com
 */

public class CurrencyRefFaker {

  public static CurrencyRefDto getInitializedCurrencyRef() {
    CurrencyRefDto itemDto = new CurrencyRefDto();

    itemDto.name = "XAF";
    itemDto.description = "FCA Central africa";

    return itemDto;
  }

  public static CurrencyRefDto getRandomCurrencyRef() {
    CurrencyRefDto itemDto = new CurrencyRefDto();
    itemDto.name =
      FakerUtils.fakeValuesService.letterify("????????????????????????");
    itemDto.description =
      FakerUtils.fakeValuesService.letterify("????????????????????????");

    return itemDto;
  }
}
