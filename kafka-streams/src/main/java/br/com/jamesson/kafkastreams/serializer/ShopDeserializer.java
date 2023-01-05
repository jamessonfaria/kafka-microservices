package br.com.jamesson.kafkastreams.serializer;

import br.com.jamesson.kafkastreams.dto.ShopDTO;
import com.google.gson.Gson;
import java.nio.charset.Charset;
import org.apache.kafka.common.serialization.Deserializer;

public class ShopDeserializer implements Deserializer {

  private static final Charset CHARSET = Charset.forName("UTF-8");
  private static Gson gson = new Gson();

  public Object deserialize(String s, byte[] bytes) {

    try {
      String shop = new String(bytes, CHARSET);
      return gson.fromJson(shop, ShopDTO.class);
    } catch (Exception e) {
      throw new IllegalArgumentException("Error reading bytes!", e);
    }

  }
}
