package com.hjh.demo.rabbitMQ;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * 生产者
 */
public class Producer {

	public static void main(String args[]) throws IOException, TimeoutException {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("127.0.0.1");
		factory.setPort(AMQP.PROTOCOL.PORT);
		factory.setUsername("userName");
		factory.setPassword("password");

		String exchange = "demo";
		String route = "warn";

		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		try {
			// 声明交换机
			channel.exchangeDeclare("demo", BuiltinExchangeType.DIRECT, true);
			// 发送消息 - 消息会通过交换机按路由地址分发到与它绑定的队列里面
			channel.basicPublish(exchange, route, MessageProperties.PERSISTENT_BASIC, "message content".getBytes("utf-8"));
		} finally {
			channel.close();
			connection.close();
		}

	}

}
