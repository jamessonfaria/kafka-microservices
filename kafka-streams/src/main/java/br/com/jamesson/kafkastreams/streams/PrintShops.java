package br.com.jamesson.kafkastreams.streams;

import br.com.jamesson.kafkastreams.serializer.ShopSerde;
import java.time.Duration;
import java.util.Properties;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.TimeWindowedKStream;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.apache.kafka.streams.kstream.Windowed;

public class PrintShops {

  private static final String SHOP_TOPIC = "SHOP_TOPIC";

  public static void main(String[] args) {

    Properties props = new Properties();
    //props.put(StreamsConfig.APPLICATION_ID_CONFIG, "show-shops");
    props.put(StreamsConfig.APPLICATION_ID_CONFIG, "count-shops-by-users");
    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
    props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, ShopSerde.class.getName());

    StreamsBuilder builder = new StreamsBuilder();
    KStream inputTopic = builder.stream(SHOP_TOPIC);

    inputTopic.print(Printed.toSysOut());

    KafkaStreams streams = new KafkaStreams(builder.build(), props);
    streams.start();

  }

}
