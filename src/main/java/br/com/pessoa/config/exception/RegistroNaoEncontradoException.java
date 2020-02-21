package br.com.pessoa.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RegistroNaoEncontradoException extends RuntimeException {
    public RegistroNaoEncontradoException(Object id) {
        super("Registro não encontrado com o id " + id);
    }
}
