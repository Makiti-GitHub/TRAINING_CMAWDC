package com.makiti.test.currency.dtos;

import com.makiti.test.currency.models.CurrencyRef;

/**
 * @author franck.keudem@gmail.com
 */

public class CurrencyRefDto {

  public Long id;
  public String name;
  public String description;

  public static CurrencyRefDto getInstance(CurrencyRef item) {
    CurrencyRefDto itemDto = new CurrencyRefDto();
    itemDto.id = item.getId();
    itemDto.name = item.getName();
    itemDto.description = item.getDescription();

    return itemDto;
  }

  public static CurrencyRef getInstance(CurrencyRefDto itemDto) {
    CurrencyRef item = new CurrencyRef();
    item.setId(itemDto.id);
    item.setName(itemDto.name);
    item.setDescription(itemDto.description);

    return item;
  }
}
