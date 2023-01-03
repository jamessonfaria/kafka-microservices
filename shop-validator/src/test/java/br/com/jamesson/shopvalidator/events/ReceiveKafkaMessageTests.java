package br.com.jamesson.shopvalidator.events;

import br.com.jamesson.shopvalidator.dto.ShopDTO;
import br.com.jamesson.shopvalidator.dto.ShopItemDTO;
import br.com.jamesson.shopvalidator.model.Product;
import br.com.jamesson.shopvalidator.repository.ProductRepository;
import javax.swing.Spring;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ReceiveKafkaMessageTests {

  @InjectMocks
  private ReceiveKafkaMessage receiveKafkaMessage;

  @Mock
  private KafkaTemplate<String, ShopDTO> kafkaTemplate;

  @Mock
  private ProductRepository productRepository;

  private static final String SHOP_TOPIC_EVENT_NAME = "SHOP_TOPIC_EVENT";

  public ShopDTO getShopDTO() {
    ShopDTO shopDTO = new ShopDTO();
    shopDTO.setBuyerIdentifier("b-1");

    ShopItemDTO shopItemDTO = new ShopItemDTO();
    shopItemDTO.setAmount(1000);
    shopItemDTO.setProductIdentifier("product-1");
    shopItemDTO.setPrice((float) 100);

    shopDTO.getItems().add(shopItemDTO);
    return shopDTO;
  }

  public Product getProduct() {
    Product product = new Product();
    product.setAmount(1000);
    product.setId(1L);
    product.setIdentifier("product-1");
    return product;
  }

  @Test
  void testProcessShopSuccess() {
    ShopDTO shopDTO = getShopDTO();
    Product product = getProduct();

    Mockito.when(productRepository.findByIdentifier("product-1"))
        .thenReturn(product);

    receiveKafkaMessage.listenShopTopic(shopDTO, "", "", "");

    Mockito.verify(kafkaTemplate, Mockito.times(1))
        .send(SHOP_TOPIC_EVENT_NAME, shopDTO);

    Assertions.assertThat(shopDTO.getStatus()).isEqualTo("SUCCESS");
  }

  @Test
  void testProcessError() {
    ShopDTO shopDTO = getShopDTO();

    Mockito.when(productRepository.findByIdentifier("product-1"))
        .thenReturn(null);

    receiveKafkaMessage.listenShopTopic(shopDTO, "", "", "");

    Mockito.verify(kafkaTemplate, Mockito.times(1))
        .send(SHOP_TOPIC_EVENT_NAME, shopDTO);

    Assertions.assertThat(shopDTO.getStatus()).isEqualTo("ERROR");
  }

}
