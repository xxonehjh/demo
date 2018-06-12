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

		// 声明交换机
		channel.exchangeDeclare("demo", BuiltinExchangeType.DIRECT, true);
		// 声明队列
		channel.queueDeclare(queue, true, false, false, null);
		// 指定路由绑定队列和交换机
		channel.queueBind(queue, exchange, route);
		// 监听消息
		channel.basicConsume(queue, new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				System.out.println("收到消息:" + new String(body, "utf-8"));
			}
		});

	}

}