package br.com.jamesson.shopapi.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopDTO {

  private String identifier;
  private String status;
  private LocalDate dateShop;
  private List<ShopItemDTO> items;

}
