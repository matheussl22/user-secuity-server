package br.com.pessoa.domain.pessoa;

import br.com.pessoa.config.support.entity.EntityAudit;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa extends EntityAudit {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sexo")
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @Column(name = "email")
    private String email;

    @Column(name = "data_nascimento")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dataNascimento;

    @Column(name = "naturalidade")
    private String naturalidade;

    @Column(name = "nacionalidade")
    private String nacionalidade;

    @Column(name = "cpf")
    private String cpf;

}
