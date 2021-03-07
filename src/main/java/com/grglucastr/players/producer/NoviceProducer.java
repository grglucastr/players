package com.grglucastr.players.producer;

import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Component
public class NoviceProducer implements PlayerProducer {

    private static final String TOPIC_NAME = "novice-players";

    @Value("${kafka.bootstrap-server.address}")
    private String bootstrapServer;

    @Value("${kafka.bootstrap-server.port}")
    private String bootstrapServerPort;

    private Properties properties;

    @PostConstruct
    public void init(){

        if(StringUtils.isBlank(bootstrapServer)){
            bootstrapServer = "127.0.0.1";
        }

        if(StringUtils.isBlank(bootstrapServerPort)){
            bootstrapServer = "9092";
        }

        String server = bootstrapServer + ":" + bootstrapServerPort;

        this.properties = new Properties();
        this.properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, server);
        this.properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        this.properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    }

    @Override
    public void postContentInTopic(String content) {

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, content);

        producer.send(record);

        producer.flush();
        producer.close();
    }
}