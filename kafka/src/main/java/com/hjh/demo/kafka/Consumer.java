package com.hjh.demo.kafka;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 * 消费者
 * 
 * @author xxonehjh@163.com
 *
 */
public class Consumer {

	public static void main(String args[]) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", "test");
		props.put("enable.auto.commit", false);
		props.put("auto.commit.interval.ms", 1000);
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
		try {
			consumer.subscribe(Arrays.asList("test"));
			while (true) {
				ConsumerRecords<String, String> records = consumer.poll(100);
				if (records.isEmpty()) {
					continue;
				}
				for (ConsumerRecord<String, String> record : records) {
					System.out.println("rec:" + record.topic() + "->" + record.value());
				}
				consumer.commitSync();
			}
		} finally {
			consumer.close();
		}
	}

}
