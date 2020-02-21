package br.com.pessoa.domain.usuario;

import br.com.pessoa.config.support.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UsuarioServiceImpl extends AbstractService<Usuario, String, UsuarioRepository>
        implements UsuarioService, UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return usuarioRepository.findById(s).get();

    }
}
