package br.com.jamesson.shopapi.model;

import br.com.jamesson.shopapi.dto.ShopDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Shop {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String identifier;
  private String status;

  @Column(name = "buyer_identifier")
  private String buyerIdentifier;

  @Column(name = "date_shop")
  private LocalDate dateShop;

  @OneToMany(fetch = FetchType.EAGER,
        cascade = CascadeType.ALL,
        mappedBy = "shop" )
  private List<ShopItem> items;

  public static Shop convert(ShopDTO shopDTO) {
    Shop shop = new Shop();
    shop.setIdentifier(shopDTO.getIdentifier());
    shop.setStatus(shopDTO.getStatus());
    shop.setDateShop(shopDTO.getDateShop());
    shop.setBuyerIdentifier(shopDTO.getBuyerIdentifier());
    shop.setItems(shopDTO.getItems() != null ?
        shopDTO.getItems()
        .stream()
        .map(ShopItem::convert)
        .toList() : new ArrayList<>()
    );

    return shop;
  }
}
