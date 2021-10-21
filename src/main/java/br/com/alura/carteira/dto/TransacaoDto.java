package br.com.alura.carteira.dto;

import br.com.alura.carteira.modelo.TipoTransacao;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransacaoDto {

    private Long id;
    private String ticker;
    private BigDecimal preco;
    private Integer quantidade;
    private TipoTransacao tipo;


}
