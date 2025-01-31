package com.miseventospe.backend.servicios;

import com.miseventospe.backend.dto.LoginDTO;
import com.miseventospe.backend.dto.UsuarioRegistroDTO;
import com.miseventospe.backend.entidades.Usuario;
import com.miseventospe.backend.enumeraciones.Rol;
import com.miseventospe.backend.excepciones.MiExcepcion;
import com.miseventospe.backend.repositorios.UsuarioRepositorio;
import com.miseventospe.backend.seguridad.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UsuarioServicio {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Transactional
    public void registrarUsuario(UsuarioRegistroDTO usuarioRegistroDTO) throws MiExcepcion{
        validar(usuarioRegistroDTO.getNombre(), usuarioRegistroDTO.getApellido(), usuarioRegistroDTO.getEmail(), usuarioRegistroDTO.getPassword(), usuarioRegistroDTO.getPassword2());

        Usuario usuario = new Usuario();

        usuario.setNombre(usuarioRegistroDTO.getNombre());
        usuario.setApellido(usuarioRegistroDTO.getApellido());
        usuario.setEmail(usuarioRegistroDTO.getEmail());
        usuario.setPassword(new BCryptPasswordEncoder().encode(usuarioRegistroDTO.getPassword()));
        usuario.setRol(Rol.USER);

        usuarioRepositorio.save(usuario);

    }

    @Transactional(readOnly = true)
    public String autenticarUsuario(LoginDTO loginDTO) throws MiExcepcion {
        Usuario usuario = usuarioRepositorio.buscarPorEmail(loginDTO.getEmail());
        if(!passwordEncoder.matches(loginDTO.getPassword(), usuario.getPassword())){
            throw new MiExcepcion("Credenciales incorrectas");
        }

        return jwtUtil.generateToken(usuario.getEmail());
    }


    public void validar(String nombre, String apellido, String email, String password, String password2) throws MiExcepcion{
        if (nombre == null || nombre.isEmpty() ) {
            throw new MiExcepcion("el nombre no puede ser nulo o estar vacío");
        }
        if (apellido == null || apellido.isEmpty() ) {
            throw new MiExcepcion("el apellido no puede ser nulo o estar vacío");
        }
        if (email == null || email.isEmpty() ) {
            throw new MiExcepcion("el email no puede ser nulo o estar vacío");
        }
        if (password == null || password.isEmpty()  || password.length() <= 5 || password.length() >= 13) {
            throw new MiExcepcion("La contraseña no puede estar vacía, y debe tener como máximo 12 dígitos");
        }
        if (!password.equals(password2)) {
            throw new MiExcepcion("Las contraseñas ingresadas deben ser iguales");
        }
    }
}
