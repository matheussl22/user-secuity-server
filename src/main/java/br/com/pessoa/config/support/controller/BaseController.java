package br.com.pessoa.config.support.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;

public interface BaseController<E, K extends Serializable> {

    @RequestMapping(method = RequestMethod.GET)
    Page<E> listarPagina(@PageableDefault(page = 0, size = 10) Pageable pageable);

    @RequestMapping(method = RequestMethod.POST)
    E incluir(@RequestBody E e);

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    Object buscarPorId(@PathVariable K id);

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    Object atualizar(@RequestBody E e, @PathVariable K id);

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    Object excluir(@PathVariable K id);

}
