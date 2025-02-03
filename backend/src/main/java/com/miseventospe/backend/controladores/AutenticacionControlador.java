package com.miseventospe.backend.controladores;

import com.miseventospe.backend.dto.LoginDTO;
import com.miseventospe.backend.dto.UsuarioRegistroDTO;
import com.miseventospe.backend.excepciones.MiExcepcion;
import com.miseventospe.backend.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/auth")
public class AutenticacionControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostMapping("/registro")
    public ResponseEntity<Object> registrarUsuario(@RequestBody UsuarioRegistroDTO usuarioRegistroDTO){
        try {
            usuarioServicio.registrarUsuario(usuarioRegistroDTO);
            return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.OK);
        } catch (MiExcepcion e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO){
        try {
            String token = usuarioServicio.autenticarUsuario(loginDTO);
            return ResponseEntity.ok(token);
        } catch (MiExcepcion e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e){
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
