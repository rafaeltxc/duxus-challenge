package br.com.duxusdesafio.repositories;

import br.com.duxusdesafio.model.domain.ComposicaoTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComposicaoTimeRepository extends JpaRepository<ComposicaoTime, Long> {
}
