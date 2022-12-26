package br.com.jamesson.shopvalidator.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopItemDTO {

  private String productIdentifier;
  private Integer amount;
  private Float price;

}
