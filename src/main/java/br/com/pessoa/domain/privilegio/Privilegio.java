package br.com.pessoa.domain.privilegio;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Privilegio implements GrantedAuthority {

    private static final long serialVersionUID = 1324839655945414871L;

    @Id
    @GeneratedValue(generator = "privilegio", strategy = GenerationType.TABLE)
    @TableGenerator(name = "privilegio", allocationSize = 1, schema = "public")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Override
    public String getAuthority() {
        return nome;
    }
}