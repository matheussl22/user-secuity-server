package br.com.pessoa.config.support.controller;

import br.com.pessoa.config.dto.SuccessResponse;
import br.com.pessoa.config.exception.RegistroNaoEncontradoException;
import br.com.pessoa.config.support.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.util.Objects;

/**
 * Abstract controller that encapsulates all boilerplate code needed to
 * create a simple controller object
 *
 * @param <E> entity object
 * @param <S> classecnj that implements basic operations from {@link BaseService}
 */
public abstract class AbstractController<E, K extends Serializable, S extends BaseService<E, K>> implements BaseController<E, K> {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final SuccessResponse SUCCESS_RESPONSE = new SuccessResponse();

    @Autowired
    protected S service;

    public Page<E> listarPagina(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return service.listarPagina(pageable);
    }

    public E incluir(@RequestBody E e) {
        return service.salvar(e);
    }

    public Object buscarPorId(@PathVariable K id) {
        final E entity = service.buscarPorId(id);
        if (Objects.isNull(entity)) {
            throw new RegistroNaoEncontradoException(id);
        }
        return entity;
    }

    public Object atualizar(@RequestBody E e, @PathVariable K id) {
        final E obj = service.buscarPorId(id);
        if (Objects.isNull(obj)) {
            throw new RegistroNaoEncontradoException(id);
        } else {
            return service.salvar(e);
        }
    }

    public Object excluir(@PathVariable K id) {
        if (service.excluir(id)) {
            return SUCCESS_RESPONSE;
        }
        throw new RegistroNaoEncontradoException(id);
    }


}