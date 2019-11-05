package com.fiap.microservices.netflix.servicedesk.events;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fiap.microservices.netflix.servicedesk.enums.Status;

@Component
public class SimpleSourceBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6679253087315849530L;

	private static final Logger logger = LoggerFactory.getLogger(SimpleSourceBean.class);

	@Autowired
	private ProducerKafka producerKafka;

	public void publishOrgChange(Status status, Long id, Long userId) {
		logger.debug("Enviando mensagem para o Kafka da atualização de status para {} do chamado de Id: {} para o UserId: {} ", status, id, userId);
		producerKafka.produce("OrderDTO", id+","+userId+","+status);
	}
	
}
