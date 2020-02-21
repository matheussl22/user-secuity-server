package br.com.pessoa.domain.privilegio;

import br.com.pessoa.config.support.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
interface PrivilegioRepository extends BaseRepository<Privilegio, Long> {
}
