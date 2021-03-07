package com.grglucastr.players.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class NoviceProducer implements PlayerProducer {

    private static final String BOOTSTRAP_SERVER = "127.0.0.7:9092";
    private static final String TOPIC_NAME = "novice-players";

    private Properties properties;

    public NoviceProducer(){
        this.properties = new Properties();
        this.properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
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