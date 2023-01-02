package br.com.jamesson.shopretry.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopDTO {

  private String identifier;
  private String status;
  private LocalDate dateShop;
  private String buyerIdentifier;
  private List<ShopItemDTO> items = new ArrayList<>();

}
