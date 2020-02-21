package br.com.pessoa.config.support.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

public interface BaseService<T, K extends Serializable> {

    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
     * entity instance completely.
     *
     * @param entity
     * @return the saved entity
     */

    T salvar(T entity);

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    Page<T> listarPagina(Pageable pageable);

    /**
     * Deletes a given entity.
     *
     * @param id
     */
    Boolean excluir(K id);

    /**
     * View a given entity.
     *
     * @param id
     */
    T buscarPorId(K id);
}
