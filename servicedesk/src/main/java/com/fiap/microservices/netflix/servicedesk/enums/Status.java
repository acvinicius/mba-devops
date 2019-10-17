package com.fiap.microservices.netflix.servicedesk.enums;

import lombok.Getter;

@Getter
public enum Status {

	ABERTO("Aberto", 1),
	EM_ANALISE("Em análise", 2),
	CONCLUIDO("Concluído", 3),
	CANCELADO("Cancelado", 4);
	
	private String value;
	private int code;
	
	Status (String value, int code) {
		this.value = value;
		this.code = code;
	}
	
}
