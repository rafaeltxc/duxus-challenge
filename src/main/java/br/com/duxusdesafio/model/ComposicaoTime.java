package br.com.duxusdesafio.model;


import lombok.*;

import javax.persistence.*;
import java.util.Objects;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tbl_composicao_time")
public class ComposicaoTime {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cp_time")
	private long composicaoId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_time")
	private Time time;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_integrante")
	private Integrante integrante;

	public ComposicaoTime(Time time, Integrante integrante) {
		this.time = time;
		this.integrante = integrante;
	}
}
