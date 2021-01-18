package com.sanoaf.rabbitmq.send;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Profile("send")
public class SendMsg {
	static Logger logger = LoggerFactory.getLogger(SendMsg.class);

	private static String QUEUE_NAME = "hello";

	public static void main(String args[]) {
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("localhost");
			Connection conn = factory.newConnection();
			Channel channel = conn.createChannel();
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			String msg = "This is a msg";
			channel.basicPublish("", QUEUE_NAME, null, msg.getBytes("UTF-8"));
			logger.info("Send :: " + msg);
			channel.close();
			conn.close();
		} catch (IOException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}