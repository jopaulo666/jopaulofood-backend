package br.com.jopaulo.jopaulofood.domain.cliente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import br.com.jopaulo.jopaulofood.domain.usuario.Usuario;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
@Table(name = "cliente")
public class Cliente extends Usuario{

	@NotBlank(message = "O CPF é obrigatório")
	@Pattern(regexp = "[0-9]{10,11}", message = "CPF inválido")
	@Column(length = 11, nullable = false)
	private String cpf;
	
	@NotBlank(message = "O CEP é obrigatório")
	@Pattern(regexp = "[0-9]{8}", message = "CEP inválido")
	@Column(length = 8)
	private String cep;
}
