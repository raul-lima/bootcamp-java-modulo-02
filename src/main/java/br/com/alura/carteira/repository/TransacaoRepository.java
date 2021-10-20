package br.com.alura.carteira.repository;

import br.com.alura.carteira.dto.ItemCarteiraDto;
import br.com.alura.carteira.modelo.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {


    @Query("select new br.com.alura.carteira.dto.ItemCarteiraDto(t.ticker, " +
            "sum(CASE WHEN(t.tipo = 'COMPRA') THEN t.quantidade ELSE (t.quantidade * -1) END), " +
            "(select sum(CASE WHEN(t2.tipo = 'COMPRA') THEN t2.quantidade ELSE (t2.quantidade * -1) END) from Transacao t2))" +
            " from Transacao t group by t.ticker")
    List<ItemCarteiraDto> relatorioCarteiraDeInvestimentos();
}

