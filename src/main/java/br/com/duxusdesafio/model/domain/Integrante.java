package br.com.duxusdesafio.model.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

	@OneToMany(mappedBy = "integrante", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ComposicaoTime> composicaoTime;

	public Integrante(String franquia, String nome, String funcao, List<ComposicaoTime> composicaoTime) {
		this.franquia = franquia;
		this.nome = nome;
		this.funcao = funcao;
		this.composicaoTime = composicaoTime;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Integrante)) return false;
		Integrante that = (Integrante) o;
		return integranteId == that.integranteId && Objects.equals(franquia, that.franquia) && Objects.equals(nome, that.nome) && Objects.equals(funcao, that.funcao);
	}

	@Override
	public int hashCode() {
		return Objects.hash(integranteId, franquia, nome, funcao);
	}

	@Override
	public String toString() {
		return "Integrante{" +
				"id=" + integranteId +
				", franquia='" + franquia + '\'' +
				", nome='" + nome + '\'' +
				", funcao='" + funcao + '\'' +
				'}';
	}
}
