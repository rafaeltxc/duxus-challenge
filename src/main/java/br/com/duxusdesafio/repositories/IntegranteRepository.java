package br.com.duxusdesafio.repositories;

import br.com.duxusdesafio.model.domain.Integrante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntegranteRepository extends JpaRepository<Integrante, Long> {
}
