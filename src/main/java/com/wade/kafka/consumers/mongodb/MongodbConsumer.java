package com.wade.kafka.consumers.mongodb;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * MongodbConsumer
 */
@Component
public class MongodbConsumer {

    private static final Logger logger = LoggerFactory.getLogger(MongodbConsumer.class);

    @Autowired
    GroupMeMessageRepository repository;

    @KafkaListener(topics = "test")
    public void listen(ConsumerRecord<String, String> cr) throws JsonParseException, JsonMappingException, IOException {
        logger.error("Received message from Kafka: " + cr.toString());
        ObjectMapper objectMapper = new ObjectMapper();
        GroupMeMessage message = objectMapper.readValue(cr.value(), GroupMeMessage.class);

        repository.save(message);
    }
}