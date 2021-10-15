package br.com.alura.carteira.dto;

import br.com.alura.carteira.modelo.TipoTransacao;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoFormDto {

    @NotBlank
    @Size(min = 5, max = 6)
    @Pattern(regexp = "[a-zA-Z]{4}[0-9][0-9]?", message = "{transacao.ticker.invalido}")
    private String ticker;
    @NotNull
    private BigDecimal preco;

    @PastOrPresent
    @NotNull
    private LocalDate data;
    @NotNull
    private int quantidade;
    @NotNull
    private TipoTransacao tipo;

    @JsonAlias("usuario_id")
    private Long usuarioId;
}
