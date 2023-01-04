package br.com.jamesson.shopreport.events;

import br.com.jamesson.shopreport.dto.ShopDTO;
import br.com.jamesson.shopreport.repository.ReporterRepository;
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
  private ReporterRepository reportRepository;

  public ShopDTO getShopDTO() {
    ShopDTO shopDTO = new ShopDTO();
    shopDTO.setStatus("SUCCESS");
    return shopDTO;
  }

  @Test
  void testProcessShopSuccess() {
    ShopDTO shopDTO = getShopDTO();

    receiveKafkaMessage.listenShopTopic(shopDTO);

    Mockito.verify(reportRepository, Mockito.times(1))
        .incrementShopStatus(shopDTO.getStatus());
  }

}
