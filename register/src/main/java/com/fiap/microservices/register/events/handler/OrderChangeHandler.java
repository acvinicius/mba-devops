package com.fiap.microservices.register.events.handler;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.fiap.microservices.register.events.CustomChannels;
import com.fiap.microservices.register.model.OrderChangeDTO;

@EnableBinding(CustomChannels.class)
public class OrderChangeHandler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8559586744863997438L;

	private static final Logger logger = LoggerFactory.getLogger(OrderChangeHandler.class);
	
	@StreamListener("teste")
	public void loggerSink(OrderChangeDTO ordUpdated) {
		logger.debug("Recebida atualização de Status do chamado de id: " + ordUpdated.getId());
		switch (ordUpdated.getStatus()) {
		case ABERTO:
			logger.debug("Atualizado status para ABERTO do usuário de id {}",
					ordUpdated.getUserId());
			break;
		case EM_ANALISE:
			logger.debug("Atualizado status para EM ANÁLISE do usuário de id {}",
					ordUpdated.getUserId());
			break;
		case CONCLUIDO:
			logger.debug("Atualizado status para CONCLUÍDO do usuário de id {}",
					ordUpdated.getUserId());
			break;
		case CANCELADO:
			logger.debug("Atualizado status para CANCELADO do usuário de id {}",
					ordUpdated.getUserId());
			break;
		default:
			logger.error("Recebido uma mensagem inesperada do chamado de id ", ordUpdated.getId());
			break;
		}
	}
	
}
