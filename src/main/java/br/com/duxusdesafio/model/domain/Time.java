package br.com.duxusdesafio.model.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
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
}
