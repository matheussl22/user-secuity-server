package br.com.pessoa.domain.usuario;

import br.com.pessoa.domain.privilegio.Privilegio;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Usuario implements UserDetails {

    @Id
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "account_non_expired")
    private boolean accountNonExpired;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked;

    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired;

    @Column(name = "enabled")
    private boolean enabled;

    @Override
    public Collection<Privilegio> getAuthorities() {
        Collection<Privilegio> lista = new ArrayList<>();
        lista.add(Privilegio.builder().nome("USER").build());
        return lista;
    }

}
