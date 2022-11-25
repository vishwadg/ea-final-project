package edu.miu.movieservice.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String kafkaProducerServers;


    public Map<String, Object> ratingProducerConfig() {
        Map<String, Object> rpConfig = new HashMap<>();
        rpConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProducerServers);
        rpConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        rpConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return rpConfig;
    }

    @Bean
    public <T> ProducerFactory<String, T> producerFactory() {
        return new DefaultKafkaProducerFactory<>(ratingProducerConfig());
    }

    @Primary
    @Bean
    public <T> KafkaTemplate<String, T> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}