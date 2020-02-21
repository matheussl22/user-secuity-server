package br.com.pessoa.config.support;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository<E, K extends Serializable> extends JpaRepository<E, K> {

}