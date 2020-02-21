package br.com.pessoa.config.support.service;

import br.com.pessoa.config.support.BaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.persistence.jpa.jpql.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Abstract classecnj that encapsulates all boilerplate code needed to create a
 * simple classecnj object
 *
 * @param <E> entity object
 * @param <R> repository that implements basic operations from
 *            {@link CrudRepository}
 * @param <K>
 * @param <K> tipo da chave primária
 */

@Slf4j
public abstract class AbstractService<E, K extends Serializable, R extends BaseRepository<E, K>> implements BaseService<E, K> {

    @Autowired
    protected R repo;

    protected void validarEntity(E entity) {

    }

    @Override
    @Transactional
    public E salvar(E entity) {
        return repo.save(entity);
    }

    @Override
    public Page<E> listarPagina(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    @Transactional
    public Boolean excluir(K id) {
        try {
            log.debug("Excluindo objeto com id: " + id);
            repo.deleteById(id);
            return Boolean.TRUE;
        } catch (ConstraintViolationException e) {
            Iterator<ConstraintViolation<?>> iterator = e.getConstraintViolations().iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<?> cv = iterator.next();
                String erro = cv.getRootBeanClass().getName() + "." + cv.getPropertyPath() + " " + cv.getMessage();
                Assert.fail(erro);
            }
        }
        return false;
    }

    public void flush() {
        repo.flush();
    }

    @Override
    public E buscarPorId(K id) {
        E entity = null;
        try {
            entity = repo.findById(id).get();

        } catch (Exception e) {
            Optional<Throwable> rootCause = Stream.iterate(e, Throwable::getCause)
                    .filter(element -> element.getCause() == null).findFirst();
            if (rootCause.isPresent() && (rootCause.get() instanceof ConstraintViolationException)) {
                ConstraintViolationException excecao = (ConstraintViolationException) rootCause.get();
                Iterator<ConstraintViolation<?>> iterator = excecao.getConstraintViolations().iterator();
                while (iterator.hasNext()) {
                    ConstraintViolation<?> cv = iterator.next();
                    String erro = cv.getRootBeanClass().getName() + "." + cv.getPropertyPath() + " "
                            + cv.getMessage();
                    Assert.fail(erro);
                }
            }

            throw e;
        }
        return entity;
    }

    public R getRepository() {
        return repo;
    }

}