package br.com.pessoa.controller;

import br.com.pessoa.config.support.controller.AbstractController;
import br.com.pessoa.domain.pessoa.Pessoa;
import br.com.pessoa.domain.pessoa.PessoaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoas")
public class PessoaController extends AbstractController<Pessoa, Long, PessoaService> {

}
