package br.com.pessoa.domain.privilegio;

import br.com.pessoa.config.support.service.AbstractService;
import org.springframework.stereotype.Service;

@Service
class PrivilegioServiceImpl extends AbstractService<Privilegio, Long, PrivilegioRepository> implements PrivilegioService {
}
