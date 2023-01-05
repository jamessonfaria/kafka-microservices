package br.com.jamesson.kafkastreams.serializer;

import com.google.gson.Gson;
import java.nio.charset.Charset;
import org.apache.kafka.common.serialization.Serializer;

public class ShopSerializer implements Serializer {

  private static final Charset CHARSET = Charset.forName("UTF-8");
  private static Gson gson = new Gson();


  @Override
  public byte[] serialize(String s, Object o) {
    String line = gson.toJson(o);
    return line.getBytes(CHARSET);
  }
}
