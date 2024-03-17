package br.com.duxusdesafio.model.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_composicao_time")
public class ComposicaoTime {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cp_time")
	private Long composicaoId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_time")
	@JsonIgnore
	private Time time;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_integrante")
	@JsonIgnore
	private Integrante integrante;

	public ComposicaoTime(Time time, Integrante integrante) {
		this.time = time;
		this.integrante = integrante;
	}

	@Override
	public final boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ComposicaoTime)) return false;
		ComposicaoTime that = (ComposicaoTime) o;
		return composicaoId == that.composicaoId && Objects.equals(time, that.time) && Objects.equals(integrante, that.integrante);
	}

	@Override
	public final int hashCode() {
		return Objects.hash(composicaoId, time, integrante);
	}

	@Override
	public String toString() {
		return "ComposicaoTime{" +
				"time=" + time +
				", integrante=" + integrante +
				'}';
	}
}
