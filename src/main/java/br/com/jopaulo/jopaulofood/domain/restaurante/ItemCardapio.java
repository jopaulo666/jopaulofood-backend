package br.com.jopaulo.jopaulofood.domain.restaurante;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import br.com.jopaulo.jopaulofood.infrastructure.web.validator.UploadContraint;
import br.com.jopaulo.jopaulofood.util.FileType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Table(name = "item_cardapio")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemCardapio implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "O nome é obrigatório")
	@Size(max = 50)
	private String nome;
	
	@NotBlank(message = "A categoria é obrigatória")
	@Size(max = 50)
	private String categoria;
	
	@NotBlank(message = "A descrição é obrigatória")
	@Size(max = 80)
	private String descricao;
	
	@Size(max = 50)
	private String imagem;
	
	@NotNull(message = "O preço é obrigatório")
	@Min(0)
	private BigDecimal preco;
	
	@NotNull
	private Boolean destaque;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "restaurante_id")
	private Restaurante restaurante;
	
	@UploadContraint(acceptedTypes = FileType.PNG, message = "Arquivo inválido. Use somente arquivos com extensão PNG")
	private transient MultipartFile imagemFile;
	
	public void setImagemFileName() {
		if (getId() == null) {
			throw new IllegalStateException("É preciso gravar o produto");
		}
		this.imagem = String.format("%04d-comida.%s", getId(), getId(), FileType.of(imagemFile.getContentType()).getExtension());
	}
}
