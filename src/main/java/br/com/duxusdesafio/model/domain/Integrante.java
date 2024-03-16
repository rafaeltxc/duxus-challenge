package br.com.duxusdesafio.model.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tbl_integrante")
public class Integrante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_integrante")
	private Long integranteId;
	
	@NotNull
	@Column(name = "nm_integrante")
	private String nome;
	
	@NotNull
	@Column(name = "it_funcao")
	private String funcao;

	@NotNull
	@Column(name = "it_franquia")
	private String franquia;

	@OneToMany(mappedBy = "integrante", fetch = FetchType.EAGER)
	private List<ComposicaoTime> composicaoTime;

	public Integrante(String franquia, String nome, String funcao, List<ComposicaoTime> composicaoTime) {
		this.franquia = franquia;
		this.nome = nome;
		this.funcao = funcao;
		this.composicaoTime = composicaoTime;
	}
}
