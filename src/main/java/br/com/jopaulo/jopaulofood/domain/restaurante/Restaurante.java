package br.com.jopaulo.jopaulofood.domain.restaurante;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.jopaulo.jopaulofood.domain.usuario.Usuario;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
@Table(name = "restaurante")
public class Restaurante extends Usuario{

	@NotBlank(message = "O CNPJ é obrigatório")
	@Pattern(regexp = "[0-9]{14}", message = "CNPJ inválido")
	@Column(length = 11, nullable = false)
	private String cnpj;
	
	@Size(max = 80)
	private String logotipo;
	
	@NotNull(message = "Taxa de entrega é obrigatório")
	@Min(0)
	@Max(99)
	private BigDecimal taxaEntregaBigDecimal;
	
	@NotNull(message = "Tempo de entrega é obrigatório")
	@Min(0)
	@Max(120)
	private Integer tempoEntregaBase;
}
