package br.com.jopaulo.jopaulofood.domain.usuario;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario implements Serializable{

	@EqualsAndHashCode.Include
	private Integer id;
	
	private String nome;
	
	private String email;
	
	private String senha;
	
	private String fone;
}
