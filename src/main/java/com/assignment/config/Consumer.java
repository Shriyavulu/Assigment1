package com.assignment.config;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Consumer {
	private static final String topic = "assignment";

	@KafkaListener(topics = topic, groupId = "group-id")
	public void consume(String message) {
		log.info("Message read as " + message);

	}
}