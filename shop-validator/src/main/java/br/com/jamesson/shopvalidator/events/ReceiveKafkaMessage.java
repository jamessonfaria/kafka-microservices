package br.com.jamesson.shopvalidator.events;

import br.com.jamesson.shopvalidator.dto.ShopDTO;
import br.com.jamesson.shopvalidator.dto.ShopItemDTO;
import br.com.jamesson.shopvalidator.model.Product;
import br.com.jamesson.shopvalidator.repository.ProductRepository;
import java.util.concurrent.atomic.AtomicReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiveKafkaMessage {

  private final KafkaTemplate<String, ShopDTO> kafkaTemplate;

  private static final String SHOP_TOPIC_NAME = "SHOP_TOPIC";
  private static final String SHOP_TOPIC_EVENT_NAME = "SHOP_TOPIC_EVENT";

  private final ProductRepository productRepository;

  @KafkaListener(topics = SHOP_TOPIC_NAME, groupId = "group")
  public void listenShopTopic(ShopDTO shopDTO) {

    try {
      log.info("Compra recebida no tópico {}.", shopDTO.getIdentifier());

      AtomicReference<Boolean> success = new AtomicReference<>(true);
      shopDTO.getItems().forEach(item -> {
        Product product = productRepository.findByIdentifier(item.getProductIdentifier());
        if (Boolean.FALSE.equals(isValidShop(item, product))) {
          shopError(shopDTO);
          success.set(false);
        }
      });

      if(Boolean.TRUE.equals(success.get()))
        shopSuccess(shopDTO);

    }catch (Exception e) {
      log.error("Erro no processamento da compra {}", shopDTO.getIdentifier());
    }

  }
  private Boolean isValidShop(ShopItemDTO item, Product product) {
    return product != null && product.getAmount() >= item.getAmount();
  }

  // Envia uma mensagem para o kafka indicando sucesso na compra
  private void shopSuccess(ShopDTO shopDTO) {
    log.info("Compra {} efetuada com sucesso.", shopDTO.getIdentifier());
    shopDTO.setStatus("SUCCESS");
    kafkaTemplate.send(SHOP_TOPIC_EVENT_NAME, shopDTO);
  }

  // Envia uma mensagem para o kafka indicando erro na compra
  private void shopError(ShopDTO shopDTO) {
    log.info("Erro no processamento da compra {}", shopDTO.getIdentifier());
    shopDTO.setStatus("ERROR");
    kafkaTemplate.send(SHOP_TOPIC_EVENT_NAME, shopDTO);














  }


}
