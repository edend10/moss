package com.eden.d.broadcaster;

import com.google.common.collect.ImmutableMap;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

@Configuration
public class BroadcasterConfig {

    @Value("${kafka.server.url}")
    private String kafkaUrl;

    @Value("${kafka.topic.name}")
    private String topicName;

    @Bean
    public Broadcaster broadcaster(KafkaTemplate<String, String> kafkaTemplate) {
        return new KafkaBroadcaster(kafkaTemplate, topicName);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = ImmutableMap.of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaUrl,
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return new DefaultKafkaProducerFactory<>(configProps);
    }
}
