package com.miseventospe.backend.servicios;

import com.miseventospe.backend.dto.EventoListarActivosDTO;
import com.miseventospe.backend.dto.EventoRegistroDTO;
import com.miseventospe.backend.entidades.Evento;
import com.miseventospe.backend.entidades.Usuario;
import com.miseventospe.backend.enumeraciones.CategoriaEvento;
import com.miseventospe.backend.enumeraciones.EstadoEvento;
import com.miseventospe.backend.excepciones.MiExcepcion;
import com.miseventospe.backend.repositorios.EventoRepositorio;
import com.miseventospe.backend.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventoServicio {

    @Autowired
    private EventoRepositorio eventoRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public void registrarEvento(EventoRegistroDTO eventoRegistroDTO) throws MiExcepcion {

        validar(eventoRegistroDTO.getTitulo(),eventoRegistroDTO.getDescripcion(),eventoRegistroDTO.getFecha(),eventoRegistroDTO.getHora(),
                eventoRegistroDTO.getUbicacion(),eventoRegistroDTO.getLatitud(),eventoRegistroDTO.getLongitud(),eventoRegistroDTO.getCategoria(),
                eventoRegistroDTO.getPrecio(), eventoRegistroDTO.getIdOrganizador());

        Evento evento = new Evento();

        evento.setTitulo(eventoRegistroDTO.getTitulo());
        evento.setDescripcion(eventoRegistroDTO.getDescripcion());
        evento.setFecha(eventoRegistroDTO.getFecha());
        evento.setHora(eventoRegistroDTO.getHora());
        evento.setUbicacion(eventoRegistroDTO.getUbicacion());
        evento.setLatitud(eventoRegistroDTO.getLatitud());
        evento.setLongitud(eventoRegistroDTO.getLongitud());
        evento.setCategoria(eventoRegistroDTO.getCategoria());

        Usuario usuarioOrganizador = usuarioRepositorio.findById(eventoRegistroDTO.getIdOrganizador()).get();
        evento.setOrganizador(usuarioOrganizador);

        evento.setEnlace(eventoRegistroDTO.getEnlace());
        evento.setPrecio(eventoRegistroDTO.getPrecio());
        evento.setEstado(EstadoEvento.ACTIVO);


        eventoRepositorio.save(evento);
    }


    @Transactional(readOnly = true)
    public List<EventoListarActivosDTO> listarEventosActivos(){
        List<Evento> eventos = eventoRepositorio.findByEstado(EstadoEvento.ACTIVO);
        return eventos.stream()
                .map(evento -> new EventoListarActivosDTO(
                        evento.getTitulo(),
                        evento.getDescripcion(),
                        evento.getFecha(),
                        evento.getHora(),
                        evento.getUbicacion(),
                        evento.getLatitud(),
                        evento.getLongitud(),
                        evento.getOrganizador().getNombre()+" "+evento.getOrganizador().getApellido(),
                        evento.getCategoria(),
                        evento.getEnlace(),
                        evento.getPrecio()
                ))
                .collect(Collectors.toList());

    }


    public void validar(String titulo, String descripcion, Date fecha, String hora,
                        String ubicacion, Double latitud, Double longitud,
                        CategoriaEvento categoria, Double precio,String idOrganizador) throws MiExcepcion {
        if (titulo == null || titulo.isEmpty() ) {
            throw new MiExcepcion("el titulo no puede ser nulo o estar vacío");
        }
        if (descripcion == null || descripcion.isEmpty() ) {
            throw new MiExcepcion("la descripcion no puede ser nulo o estar vacío");
        }
        if (fecha == null ) {
            throw new MiExcepcion("la fecha no puede ser nulo");
        }
        Date hoy = new Date();
        if (fecha.before(hoy)) {
            throw new MiExcepcion("La fecha del evento debe ser posterior a la fecha actual.");
        }

        if (hora == null || hora.isEmpty()) {
            throw new MiExcepcion("La hora no puede ser nulo");
        }
        if (ubicacion == null || ubicacion.isEmpty() ) {
            throw new MiExcepcion("la ubicacion no puede ser nulo o estar vacío");
        }
        if (latitud == null ) {
            throw new MiExcepcion("la latitud no puede ser nulo");
        }
        if (longitud == null ) {
            throw new MiExcepcion("la longitud no puede ser nulo");
        }
        if (categoria == null ) {
            throw new MiExcepcion("la categoria no puede ser nulo o estar vacío");
        }
        if (idOrganizador == null || idOrganizador.isEmpty() ) {
            throw new MiExcepcion("el organizador no puede ser nulo o estar vacío");
        }
        if (precio < 0.0 ) {
            throw new MiExcepcion("el precio no puede ser negativo");
        }

    }


}
