package com.miseventospe.backend.repositorios;

import com.miseventospe.backend.entidades.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepositorio extends JpaRepository<Evento, String> {
}
