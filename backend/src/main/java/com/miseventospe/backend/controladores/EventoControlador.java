package com.miseventospe.backend.controladores;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miseventospe.backend.dto.EventoListarActivosDTO;
import com.miseventospe.backend.dto.EventoRegistroDTO;
import com.miseventospe.backend.excepciones.MiExcepcion;
import com.miseventospe.backend.servicios.EventoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoControlador {

    @Autowired
    private EventoServicio eventoServicio;

    @PostMapping(value = "/registro", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> registrarEvento(@RequestParam("evento") String eventoJson,
                                                  @RequestParam("imagen") MultipartFile imagen){
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            EventoRegistroDTO eventoRegistroDTO = objectMapper.readValue(eventoJson, EventoRegistroDTO.class);

            eventoServicio.registrarEvento(eventoRegistroDTO, imagen);
            return new ResponseEntity<>("Evento registrado exitosamente", HttpStatus.OK);
        } catch (MiExcepcion e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listaActivos")
    public ResponseEntity<Object> listarEventosActivos(){
        try {
            List<EventoListarActivosDTO> listaEventos = eventoServicio.listarEventosActivos();
            return new ResponseEntity<>(listaEventos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
