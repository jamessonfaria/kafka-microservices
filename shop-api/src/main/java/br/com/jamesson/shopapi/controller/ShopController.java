package br.com.jamesson.shopapi.controller;

import br.com.jamesson.shopapi.dto.ShopDTO;
import br.com.jamesson.shopapi.model.Shop;
import br.com.jamesson.shopapi.repository.ShopRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/shop")
public class ShopController {

  private final ShopRepository shopRepository;

  @GetMapping
  public ResponseEntity<List<ShopDTO>> getShop() {
    List<ShopDTO> listShop = shopRepository.findAll()
        .stream()
        .map(ShopDTO::convert)
        .toList();

    return ResponseEntity.ok(listShop);
  }

  @PostMapping
  public ResponseEntity<ShopDTO> saveShop(@RequestBody  ShopDTO shopDTO) {
    shopDTO.setIdentifier(UUID.randomUUID().toString());
    shopDTO.setDateShop(LocalDate.now());
    shopDTO.setStatus("PENDING");

    Shop shop = Shop.convert(shopDTO);
    shop.getItems().forEach(i -> i.setShop(shop));

    ShopDTO shopDtoReturn = ShopDTO.convert(shopRepository.save(shop));
    return ResponseEntity.ok(shopDtoReturn);
  }

}
