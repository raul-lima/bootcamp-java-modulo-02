package br.com.alura.carteira.dto;

import br.com.alura.carteira.modelo.TipoTransacao;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class TransacaoFormDto {

    @NotNull
    @NotEmpty
    @Size(min = 5, max = 6)
    private String ticker;
    private BigDecimal preco;

    @PastOrPresent
    private LocalDate data;
    private int quantidade;
    private TipoTransacao tipo;

    @JsonAlias("usuario_id")
    private Long usuarioId;
}
