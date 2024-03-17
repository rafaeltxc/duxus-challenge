package br.com.duxusdesafio.repositories;

import br.com.duxusdesafio.model.domain.ComposicaoTime;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Classe para configuracao do repositorio da
 * entidade ComposicaoTime
 */
public interface ComposicaoTimeRepository extends JpaRepository<ComposicaoTime, Long> {
}
