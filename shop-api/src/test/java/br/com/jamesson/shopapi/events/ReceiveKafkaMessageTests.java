package br.com.jamesson.shopapi.events;

import br.com.jamesson.shopapi.dto.ShopDTO;
import br.com.jamesson.shopapi.dto.ShopItemDTO;
import br.com.jamesson.shopapi.model.Shop;
import br.com.jamesson.shopapi.repository.ShopRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ReceiveKafkaMessageTests {

  @InjectMocks
  private ReceiveKafkaMessage receiveKafkaMessage;

  @Mock
  private ShopRepository shopRepository;

  @Test
  void testSuccessfulMessageReceived() {

    ShopDTO shopDTO = new ShopDTO();
    shopDTO.setStatus("SUCCESS");

    ShopItemDTO shopItemDTO = new ShopItemDTO();
    shopItemDTO.setAmount(1000);
    shopItemDTO.setProductIdentifier("product-1");
    shopItemDTO.setPrice((float) 100);

    shopDTO.getItems().add(shopItemDTO);
    Shop shop = Shop.convert(shopDTO);

    Mockito.when(shopRepository.findByIdentifier(shopDTO.getIdentifier()))
        .thenReturn(shop);

    receiveKafkaMessage.listenShopEvents(shopDTO);

    Mockito.verify(shopRepository, Mockito.times(1))
        .findByIdentifier(shopDTO.getIdentifier());

    Mockito.verify(shopRepository, Mockito.times(1))
        .save(shop);

  }

}
