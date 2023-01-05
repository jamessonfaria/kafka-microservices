package br.com.jamesson.kafkastreams.streams;

import br.com.jamesson.kafkastreams.dto.ShopDTO;
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
import org.apache.kafka.streams.kstream.TimeWindows;
import org.apache.kafka.streams.kstream.Windowed;

public class CountShops {

  private static final String SHOP_TOPIC_EVENT = "SHOP_TOPIC";

  public static void main(String [] args) {
    Properties props = new Properties();
    props.put(StreamsConfig.APPLICATION_ID_CONFIG,
        "sum-shops-312312321");
    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,
        "localhost:9092");
    props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG,
        Serdes.String().getClass());
    props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,
        ShopSerde.class.getName());

    StreamsBuilder builder = new StreamsBuilder();
    KStream<String, ShopDTO> inputTopic =
        builder.stream(SHOP_TOPIC_EVENT);

    KTable<String, Long> teste = inputTopic.groupByKey().count(Materialized.as("count-store"));

    KTable<Windowed<String>, Long> stream = inputTopic.groupByKey()
        .windowedBy(TimeWindows.of(Duration.ofSeconds(5))).count();

    stream.toStream().print(Printed.toSysOut());

//    teste.toStream()
//        .print(Printed.toSysOut());

    KafkaStreams streams = new KafkaStreams(builder.build(), props);
    streams.start();
  }



}