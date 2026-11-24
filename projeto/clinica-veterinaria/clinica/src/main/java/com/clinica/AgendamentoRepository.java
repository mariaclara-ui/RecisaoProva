package com.clinica;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByPetNomeContainingIgnoreCaseOrderByDataHoraAsc(String nome);
}
