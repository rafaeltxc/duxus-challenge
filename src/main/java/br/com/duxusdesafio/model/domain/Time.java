package br.com.duxusdesafio.model.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_time")
public class Time {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_time")
	private Long timeId;

	@NotNull
	@Column(name = "dt_time")
    private LocalDate data;

	@Column(name = "ds_time")
	private String descricao;

	@OneToMany(mappedBy = "time", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ComposicaoTime> composicaoTime;

	public Time(LocalDate data, List<ComposicaoTime> composicaoTime) {
		this.data = data;
		this.composicaoTime = composicaoTime;
	}

	@Override
	public final boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Time)) return false;
		Time time = (Time) o;
		return timeId == time.timeId && Objects.equals(data, time.data);
	}

	@Override
	public final int hashCode() {
		return Objects.hash(timeId, data);
	}

	@Override
	public String toString() {
		return "Time{" +
				"id=" + timeId +
				", data=" + data +
				", descricao=" + descricao +
				'}';
	}
}
