/**
 * @(#)KafkaConfig.java 1.0 2017年3月22日
 * @Copyright:  Copyright 2007 - 2017 MPR Tech. Co. Ltd. All Rights Reserved.
 * @Description: 
 * 
 * Modification History:
 * Date:        2017年3月22日
 * Author:      huangjh
 * Version:     1.0.0.0
 * Description: (Initialize)
 * Reviewer:    
 * Review Date: 
 */
package com.hjh.demo.kafka;

import java.util.Properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

public class KafkaConfig implements EnvironmentAware {

    private static final Logger LOGGER = LogManager.getLogger(KafkaConfig.class);

    private Environment env;

    /**
     * kafka生产者，线程安全类
     * 
     * @return
     */
    @Bean(destroyMethod = "close")
    public KafkaProducer<String, String> kafkaProducer() {
        if (!"true".equals(env.getProperty("kafka.producer.enable"))) {
            LOGGER.warn("Kafka producer disable");
            return null;
        }
        Properties props = new Properties();
        props.put("bootstrap.servers", env.getProperty("kafka.producer.bootstrap.servers"));
        props.put("acks", env.getProperty("kafka.producer.acks"));
        props.put("retries",
                env.getProperty("kafka.producer.retries", Integer.class));
        props.put("batch.size",
                env.getProperty("kafka.producer.batch.size", Integer.class));
        props.put("linger.ms",
                env.getProperty("kafka.producer.linger.ms", Integer.class));
        props.put("buffer.memory",
                env.getProperty("kafka.producer.buffer.memory", Integer.class));
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        LOGGER.info("create kafka producer at {}", env.getProperty("kafka.producer.bootstrap.servers"));
        return new KafkaProducer<>(props);
    }

    @Bean(destroyMethod = "close")
    public KafkaConsumer<String, String> kafkaConsumer() {
        if (!"true".equals(env.getProperty("kafka.consumer.enable"))) {
            LOGGER.warn("Kafka consumer disable");
            return null;
        }
        Properties props = new Properties();
        props.put("bootstrap.servers", env.getProperty("kafka.consumer.bootstrap.servers"));
        props.put("group.id", env.getProperty("kafka.consumer.group.id"));
        props.put("enable.auto.commit",
                env.getProperty("kafka.consumer.enable.auto.commit"));
        props.put("auto.commit.interval.ms",
                env.getProperty("kafka.consumer.auto.commit.interval.ms"));
        props.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        
        LOGGER.info("create kafka consumer at {}", env.getProperty("kafka.consumer.bootstrap.servers"));
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        return consumer;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }


}
