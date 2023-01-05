package br.com.jamesson.kafkastreams.serializer;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class ShopSerde implements Serde<Object> {

  private ShopSerializer shopSerializer = new ShopSerializer();
  private ShopDeserializer shopDeserializer = new ShopDeserializer();

  @Override
  public Serializer<Object> serializer() {
    return shopSerializer;
  }

  @Override
  public Deserializer<Object> deserializer() {
    return shopDeserializer;
  }

  @Override
  public void close() {
    shopSerializer.close();
    shopDeserializer.close();
  }
}
