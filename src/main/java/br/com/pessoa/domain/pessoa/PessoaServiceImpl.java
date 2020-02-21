package br.com.pessoa.domain.pessoa;

import br.com.pessoa.config.support.service.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class PessoaServiceImpl extends AbstractService<Pessoa, Long, PessoaRepository>
        implements PessoaService {

}
