package br.com.pessoa;

import br.com.pessoa.domain.pessoa.Pessoa;
import br.com.pessoa.domain.pessoa.PessoaService;
import br.com.pessoa.domain.pessoa.Sexo;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PrincipalServer.class)
@WebAppConfiguration
@Ignore
public class PessoaServiceTest {

    static {
        System.setProperty("spring.profiles.active", "test");
        System.setProperty("security.oauth2.client.accessTokenUri", "");
        System.setProperty("security.oauth2.client.clientId", "");
        System.setProperty("security.oauth2.client.clientSecret", "");
    }

    @Autowired
    private PessoaService pessoaService;

    @Test
    public void testGetAll() {
        Page<Pessoa> list = pessoaService.listarPagina(PageRequest.of(0, 10));
        Assert.assertNotNull(list);
    }

    @Test
    public void testCreate() {
        Pessoa pessoa = Pessoa.builder().id(7L).nome("Josias").cpf("08227778986").sexo(Sexo.MASCULINO).dataNascimento(LocalDate.of(1996, 02, 02)).build();
        pessoa = pessoaService.salvar(pessoa);
        Assert.assertNotNull(pessoa);
        Assert.assertNotNull(pessoa.getId());
        Assert.assertEquals("Josias", pessoa.getNome());
    }

    @Test
    public void testUpdate() {
        Pessoa pessoa = Pessoa.builder().id(8L).nome("marcos").cpf("08227778985").sexo(Sexo.MASCULINO).dataNascimento(LocalDate.of(1996, 02, 02)).build();
        pessoa = pessoaService.salvar(pessoa);
        Pessoa pessoa2 = Pessoa.builder().id(9L).nome("marcia").cpf("07774178955").sexo(Sexo.MASCULINO).dataNascimento(LocalDate.of(1996, 02, 02)).build();
        pessoaService.salvar(pessoa);
        pessoaService.salvar(pessoa2);

        Page<Pessoa> list = pessoaService.listarPagina(PageRequest.of(0, 10));
        Assert.assertNotNull(list);
        Assert.assertTrue(list.getNumberOfElements() > 0);
        Pessoa toUpdate = list.getContent().get(0);
        toUpdate.setNome("MARCOLINO");
        toUpdate = pessoaService.salvar(toUpdate);
        Assert.assertNotNull(toUpdate);
        Assert.assertEquals("MARCOLINO", toUpdate.getNome());
    }

    @Test
    public void testDelete() {
        Pessoa pessoa = Pessoa.builder().id(99L).nome("TESTE DELETE").cpf("08224178987").sexo(Sexo.MASCULINO).dataNascimento(LocalDate.of(1996, 02, 02)).build();
        Pessoa toDelete = pessoaService.salvar(pessoa);
        Assert.assertNotNull(toDelete);
        Assert.assertNotNull(toDelete.getId());
        Assert.assertTrue(pessoaService.excluir(toDelete.getId()));
    }

}
