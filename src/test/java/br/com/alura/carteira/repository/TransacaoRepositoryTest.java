package br.com.alura.carteira.repository;

import br.com.alura.carteira.dto.ItemCarteiraDto;
import br.com.alura.carteira.modelo.TipoTransacao;
import br.com.alura.carteira.modelo.Transacao;
import br.com.alura.carteira.modelo.Usuario;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class TransacaoRepositoryTest {

    @Autowired
    private TransacaoRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    void deveriaRetornarRelatorioDeCarteiraDeInvestimentos(){

        Usuario usuario = new Usuario("Rafaela", "rafa@gmail.com", "123456");


        em.persist(usuario);

        Transacao t1 = new Transacao("ITSA4", LocalDate.now(), new BigDecimal("10.00"),
                50, TipoTransacao.COMPRA, usuario);

        em.persist(t1);


        Transacao t2 = new Transacao("ITSA5", LocalDate.now(), new BigDecimal("15.00"),
                60, TipoTransacao.COMPRA, usuario);

        em.persist(t2);

        Transacao t3 = new Transacao("ITSA6", LocalDate.now(), new BigDecimal("10.00"),
                90, TipoTransacao.COMPRA, usuario);

        em.persist(t3);

        Transacao t4 = new Transacao("ITSA7", LocalDate.now(), new BigDecimal("10.00"),
                80, TipoTransacao.COMPRA, usuario);

        em.persist(t4);



        List<ItemCarteiraDto> relatorio = repository.relatorioCarteiraDeInvestimentos();
        Assertions
                .assertThat(relatorio)
                .hasSize(4);


    }

}