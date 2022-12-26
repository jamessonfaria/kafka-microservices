package br.com.jamesson.shopapi.dto;

import br.com.jamesson.shopapi.model.Shop;
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
  private List<ShopItemDTO> items = new ArrayList<>();

  public static ShopDTO convert(Shop shop) {
    ShopDTO shopDTO = new ShopDTO();
    shopDTO.setIdentifier(shop.getIdentifier());
    shopDTO.setStatus(shop.getStatus());
    shopDTO.setDateShop(shop.getDateShop());
    shopDTO.setItems(
        shop.getItems()
            .stream()
            .map(ShopItemDTO::convert)
            .toList()
    );

    return shopDTO;
  }

}
