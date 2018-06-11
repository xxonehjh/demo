package com.hjh.demo.rabbitMQ;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/**
 * 消费者
 */
public class Consumer {

	public static void main(String args[]) throws IOException, TimeoutException {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("127.0.0.1");
		factory.setPort(AMQP.PROTOCOL.PORT);
		factory.setUsername("userName");
		factory.setPassword("password");

		String exchange = "demo";
		String queue = "demo";
		String route = "warn";

		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		try {
			channel.exchangeDeclare("demo", BuiltinExchangeType.DIRECT);
			channel.queueDeclare(queue, true, false, false, null);
			channel.queueBind(queue, exchange, route);

			channel.basicConsume(queue, new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
					System.out.println("收到消息:" + new String(body, "utf-8"));
				}
			});
		} finally {
			channel.close();
			connection.close();
		}

	}

}