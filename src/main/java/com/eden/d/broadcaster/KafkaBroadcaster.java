package com.eden.d.broadcaster;

import org.springframework.kafka.core.KafkaTemplate;

public class KafkaBroadcaster implements Broadcaster{

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topicName;

    public KafkaBroadcaster(KafkaTemplate<String, String> kafkaTemplate,
                            String topicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    public void broadcast(String message) {
        kafkaTemplate.send(topicName, message);
    }
}
