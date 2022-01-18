package com.nttdata.desafioBanco.util.error;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StandardError {

	private String mensagem;
}
