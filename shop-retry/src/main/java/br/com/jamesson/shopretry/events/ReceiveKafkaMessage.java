package br.com.jamesson.shopretry.events;

import br.com.jamesson.shopretry.dto.ShopDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReceiveKafkaMessage {

  private final KafkaTemplate<String, ShopDTO> kafkaTemplate;

  private static final String SHOP_TOPIC = "SHOP_TOPIC";
  private static final String SHOP_TOPIC_RETRY = "SHOP_TOPIC_RETRY";

  @KafkaListener(topics = SHOP_TOPIC, groupId = "group_report")
  public void listenShopTopic(ShopDTO shopDTO) throws Exception {

    try {
      log.info("Compra recebida no tópico {}.", shopDTO.getIdentifier());
      if (shopDTO.getItems() == null || shopDTO.getItems().isEmpty()) {
        log.error("Compra sem itens.");
        throw new Exception();
      }
    } catch (Exception e) {
      log.error("Erro na aplicação");
      kafkaTemplate.send(SHOP_TOPIC_RETRY, shopDTO);
    }

  }

  @KafkaListener(topics = SHOP_TOPIC_RETRY, groupId = "group_retry")
  public void listenShopTopicRetry(ShopDTO shopDTO) throws Exception {
    log.info("Retentativa de processamento: {}.", shopDTO.getIdentifier());
  }


}
