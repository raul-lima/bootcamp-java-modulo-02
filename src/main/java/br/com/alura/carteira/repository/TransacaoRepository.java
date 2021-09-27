package br.com.alura.carteira.repository;

import br.com.alura.carteira.modelo.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {


}

