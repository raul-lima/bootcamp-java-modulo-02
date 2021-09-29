package br.com.alura.carteira.repository;

import br.com.alura.carteira.dto.ItemCarteiraDto;
import br.com.alura.carteira.modelo.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {


    @Query("select new br.com.alura.carteira.dto.ItemCarteiraDto(t.ticker, sum(t.quantidade), sum(t.quantidade) * 1.0 / (select sum(t2.quantidade) from Transacao t2) * 1.0 ) from Transacao t group by t.ticker")
    List<ItemCarteiraDto> relatorioCarteiraDeInvestimentos();
}

