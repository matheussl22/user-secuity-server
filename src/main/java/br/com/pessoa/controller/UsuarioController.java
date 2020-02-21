package br.com.pessoa.controller;

import br.com.pessoa.config.support.controller.AbstractController;
import br.com.pessoa.domain.usuario.Usuario;
import br.com.pessoa.domain.usuario.UsuarioService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController extends AbstractController<Usuario, String, UsuarioService> {

    @GetMapping("/ok")
    @PreAuthorize("hasAuthority ('ADMIN')")
    public String findOk() {
        return "teste matheus";
    }

}
