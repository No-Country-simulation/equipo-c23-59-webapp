package com.miseventospe.backend.repositorios;

import com.miseventospe.backend.entidades.Evento;
import com.miseventospe.backend.enumeraciones.EstadoEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventoRepositorio extends JpaRepository<Evento, String> {

    List<Evento> findByEstado(EstadoEvento estado);
}
