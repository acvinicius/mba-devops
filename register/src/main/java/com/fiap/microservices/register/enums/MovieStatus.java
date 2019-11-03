package com.fiap.microservices.register.enums;

import lombok.Getter;

@Getter
public enum MovieStatus {

	ABERTO("Aberto", 1),
	WATCHED("Assistido", 2),
	FUTURE("Futuro", 3);

	private String value;
	private int code;

	MovieStatus (String value, int code) {
		this.value = value;
		this.code = code;
	}

}

