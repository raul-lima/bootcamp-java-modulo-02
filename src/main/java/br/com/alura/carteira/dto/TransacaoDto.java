package br.com.alura.carteira.dto;

import br.com.alura.carteira.modelo.TipoTransacao;
import br.com.alura.carteira.modelo.Transacao;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class TransacaoDto {

    private String ticker;
    private BigDecimal preco;
    private int quantidade;
    private TipoTransacao tipo;

}
